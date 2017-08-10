package chapter21_concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/10.
 */

/**
 * 创建两个Runnable，其中一个run()方法启动并调用wait()，第二个类捕获第一个Runnable对象的引用，
 * 其run()方法应该在一定秒数后，为第一个任务调用notifyAll()，从而使得第一个任务可以显示一条信息
 */

class Coop1 implements Runnable {

    public Coop1() {
        print("Constructed Coop1");
    }

    @Override
    public void run() {
        print("Coop1 going into wait");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                print("Coop1 exited wait");
            }
        }
    }
}

class Coop2 implements Runnable {

    private Runnable otherTask;

    public Coop2(Runnable otherTask) {
        this.otherTask = otherTask;
        print("Constructed Coop2");
    }

    @Override
    public void run() {
        print("Coop2 pausing 5 seconds");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            print("Coop2 calling notifyAll");
            synchronized (otherTask){
                //为第一个任务调用notifyAll()
                otherTask.notifyAll();
            }
        }
    }
}

public class E21_ThreadCooperation {

    public static void main(String[] args) {
        Runnable coop1 = new Coop1(),
                coop2 = new Coop2(coop1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(coop1);
        exec.execute(coop2);
        Thread.yield();
        exec.shutdownNow();
    }
}
