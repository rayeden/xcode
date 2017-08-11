package chapter21_concurrency.blockingqueue;

/**
 * Created by xhtc on 2017/8/11.
 */

import chapter21_concurrency.basicthreading.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * 修改TestBlockingQueue，添加一个将LiftOff放置到BlockingQueue中的任务，而不要放置在main()中
 * （生产者-消费者 队列）
 */

public class E28_TestBlockingQueues2 {

    private static void getkey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getkey(String message){
        print(message);
        getkey();
    }

    private static void test(String msg, BlockingQueue<LiftOff> queue) {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        LiftOffProducer producer = new LiftOffProducer(runner);
        exec.execute(runner);
        exec.execute(producer);
        getkey("Press 'ENTER' (" + msg + ")");
        exec.shutdownNow();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", // Unlimited size
                new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue", // Fixed size
                new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue", // Size of 1
                new SynchronousQueue<LiftOff>());
    }

}

class LiftOffProducer implements Runnable{

    private LiftOffRunner runner;

    public LiftOffProducer(LiftOffRunner runner) {
        this.runner = runner;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff(5));
        }
    }
}
