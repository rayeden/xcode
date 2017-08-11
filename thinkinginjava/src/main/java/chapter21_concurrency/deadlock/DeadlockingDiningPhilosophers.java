package chapter21_concurrency.deadlock;

/**
 * Created by xhtc on 2017/8/11.
 */

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 会死锁的哲学家就餐版本
 *
 * 死锁四个条件：
 * a、互斥条件：资源中至少有一个是不能共享的。（这里每个Chopstick一次就只能被一个Philosopher使用）
 * b、请求和保持条件：至少有一个任务必须持有一个资源，且正在等待获取一个当前别其他任务持有的资源。
 * c、不剥夺条件：必须把资源释放当做普通时间，不抢占。
 * d、循环等待条件：资源形成环形链，导致相互等待。
 *
 */
public class DeadlockingDiningPhilosophers {

    public static void main(String[] args) throws InterruptedException, IOException {
        //哲学家思考的时间越长，越不容易发生死锁
        int ponder = 100;
        //哲学家人数越多（筷子越多），越不容易发生死锁
        int size = 100;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            //size个筷子形成一个环，每个哲学家可以选择左右两边的筷子
            exec.execute(new Philosopher(sticks[i], sticks[(i+1)%size], i, ponder));
        }

        //中断死锁进程
        TimeUnit.SECONDS.sleep(5);

        //此方式也可以中断死锁进程
//        System.out.println("Press 'Enter' to quit");
//        System.in.read();

        exec.shutdownNow();
    }
}
