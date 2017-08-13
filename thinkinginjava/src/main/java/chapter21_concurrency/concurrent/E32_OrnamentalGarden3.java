package chapter21_concurrency.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 */

class Entrance3 implements Runnable {

    private final CountDownLatch latch;
    private static List<Entrance3> entrances = new ArrayList<>();
    private int number;
    private final int id;
    private static volatile boolean canceled;
    private static Count count = new Count();

    Entrance3(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
        entrances.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        latch.countDown();
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance3 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}


class Count {
    private int count = 0;
    private Random rand = new Random(47);

    // 此处如果没有synchronized，结果就不正确
    public synchronized int increment() {
        int temp = count;
        //此处的temp和yield增加了同步失败的可能性
        if (rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

public class E32_OrnamentalGarden3 {

    public static void main(String[] args) throws Exception {
        //如果设置CountDownLatch的Size为6，但是只起5个线程，就不能把latch减到0，不能唤醒main线程计算总和
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        //每个线程共享一个latch
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance3(latch, i));
        TimeUnit.SECONDS.sleep(3);
        Entrance3.cancel();
        exec.shutdown();
        //latch的线程全部结束后，main线程继续往下输出
        latch.await(); // Wait for results
        print("Total: " + Entrance3.getTotalCount());
        print("Sum of Entrances: " + Entrance3.sumEntrances());
    }
}
