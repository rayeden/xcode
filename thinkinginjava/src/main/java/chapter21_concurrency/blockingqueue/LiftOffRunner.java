package chapter21_concurrency.blockingqueue;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.BlockingQueue;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 */
public class LiftOffRunner implements Runnable{

    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        rockets = queue;
    }

    public void add(LiftOff lo) {
        try {
            //源码内已加锁同步，不需要再做显示的同步
            rockets.put(lo);
        } catch (InterruptedException e) {
            print("Interrupted during put()");
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //队列为空时挂起
                LiftOff rocket = rockets.take();
                rocket.run(); // Use this thread
            }
        } catch (InterruptedException e) {
            print("Waking from take()");
        }
        print("Exiting LiftOffRunner");
    }

}
