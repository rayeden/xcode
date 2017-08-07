package chapter21_concurrency.features;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/6.
 */
public class SimplePriorities implements Runnable {

    private int countDown = 5;

    private volatile double d;

    private int priority;

    private SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            //最低优先级
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
            //最高优先级
            exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        }
        exec.shutdown();
    }

}
