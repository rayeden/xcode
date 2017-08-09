package chapter21_concurrency.exercise;

/**
 * Created by xhtc on 2017/8/9.
 */

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * 创建一个非任务类，它用较长的时间间隔调用sleep()，创建一个任务，承载这个非任务类的方法。
 * 在main()方法中启动该任务，然后调用interrupt()来终止它。确保这个任务被安全地关闭。
 */
public class E18_InterruptSleepTask {

    public static void main(String[] args) {

        //直接用Thread创建任务，调用interrupt()中断线程
//        Thread t = new Thread(new Task());
//        t.start();
//        t.interrupt();

        ExecutorService exec = Executors.newCachedThreadPool();
        interrupt1(exec);
//        interrupt2(exec);

    }

    private static void interrupt2(ExecutorService exec){
        Future f = exec.submit(new Task());
        //调用Future的cancel()方法可以指定中断特定的线程，即在特定线程上调用interrupt()
        f.cancel(true);
        //此时调用shutdown()可以关闭线程池
        exec.shutdown();
    }

    private static void interrupt1(ExecutorService exec){
        exec.execute(new Task());
        //shutdown()方法没有调用interrupt()，不会中断线程，无法关闭线程池
//        exec.shutdown();
        //shutdownNow()方法调用interrupt()，会立刻中断sleep()的阻塞线程
        exec.shutdownNow();
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        CallSleep.callSleep();
    }
}

class CallSleep {

    private Random rand = new Random();

    private int gap = rand.nextInt(47);

    public static void callSleep() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            print("callSleep() is interrupted");
        }
    }
}
