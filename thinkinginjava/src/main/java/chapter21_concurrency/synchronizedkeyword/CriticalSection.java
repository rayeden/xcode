package chapter21_concurrency.synchronizedkeyword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xhtc on 2017/8/8.
 */

/**
 * synchronized同步方法和同步方法块，都可以达到保证数据一致性的目的
 * PairManager1的checkCounter比PairManager2要小很多，因为对方法加锁的时间更长，效率更低
 */
//非线程安全的类，把两个变量维护成相同的值
class Pair {
    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX() {
        x++;
    }

    public void incrementY() {
        y++;
    }

    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    public class PairValuesNotEqualException
            extends RuntimeException {
        public PairValuesNotEqualException() {
            super("Pair values not equal: " + Pair.this);
        }
    }

    // 检查两个变量是否相等
    public void checkState() {
        if (x != y)
            throw new PairValuesNotEqualException();
    }
}

//管理Pair的线程安全类，基类
abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage =
            Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair() {
        // Make a copy to keep the original safe:
        return new Pair(p.getX(), p.getY());
    }

    // Assume this is a time consuming operation
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ignore) {
        }
    }

    public abstract void increment();
}

//用synchronized修饰整个方法
class PairManager1 extends PairManager {
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

//同步代码块
class PairManager2 extends PairManager {
    public void increment() {
        Pair temp;
        //在this上同步，临界区效果会直接缩小在同步范围内
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}

class ExplicitPairManager1 extends PairManager {

    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

class ExplicitPairManager2 extends PairManager {

    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        Pair tmp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            tmp = getPair();
        } finally {
            lock.unlock();
        }
        store(tmp);
    }
}

//操作类，注入PairManager类
class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true)
            pm.increment();
    }

    public String toString() {
        return "Pair: " + pm.getPair() +
                " checkCounter = " + pm.checkCounter.get();
    }
}

class PairChecker implements Runnable {
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    public void run() {
        while (true) {
            //CAS 原子类对象值+1
            pm.checkCounter.incrementAndGet();
            //检查x,y是否相等
            pm.getPair().checkState();
        }
    }
}

public class CriticalSection {

    static void
    testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm1 = new PairManipulator(pman1),
                pm2 = new PairManipulator(pman2);
        PairChecker
                pcheck1 = new PairChecker(pman1),
                pcheck2 = new PairChecker(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pcheck1);
        exec.execute(pcheck2);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
        System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
//        PairManager
//                pman1 = new PairManager1(),
//                pman2 = new PairManager2();
        PairManager pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        testApproaches(pman1, pman2);
    }
}
