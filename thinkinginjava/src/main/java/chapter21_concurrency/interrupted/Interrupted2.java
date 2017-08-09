package chapter21_concurrency.interrupted;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;


/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 在ReentrantLock上阻塞的任务具备可以被中断的能力
 */
public class Interrupted2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blockeded2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}

class Blockeded2 implements Runnable {

    private BlockedMutex blockeded = new BlockedMutex();

    @Override
    public void run() {
        print("Waiting for f() in BlockedMutex");
        blockeded.f();
        print("Broken out of blocked call");
    }
}

class BlockedMutex {
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        //构造器获取创建对象自身的lock，并且从不释放这个锁
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            print("lock acquired in f()");
        } catch (InterruptedException e) {
            print("Interrupted from lock acquisition in f()");
        }
    }
}
