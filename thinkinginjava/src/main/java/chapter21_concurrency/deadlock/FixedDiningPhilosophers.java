package chapter21_concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/11.
 */

/**
 * 打破循环等待，解除死锁
 */
public class FixedDiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        int ponder = 5;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            if (i < (size - 1)) {
                exec.execute(new Philosopher(chopsticks[i], chopsticks[i + 1], i, ponder));
            } else {
                //让最后一个哲学家，先拿起和放下左边的筷子，打破循环条件，解除死锁
                exec.execute(new Philosopher(chopsticks[0], chopsticks[i], i, ponder));
            }
        }
        TimeUnit.SECONDS.sleep(6);

        exec.shutdownNow();
    }
}
