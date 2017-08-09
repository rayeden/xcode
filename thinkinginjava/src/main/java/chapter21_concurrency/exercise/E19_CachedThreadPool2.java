package chapter21_concurrency.exercise;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/9.
 */
public class E19_CachedThreadPool2 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff2());
        }
        exec.shutdownNow();
    }
}

class LiftOff2 implements Runnable {

    protected int countDown = 5000;

    private static int taskCount;

    private final int id = taskCount++;

    public LiftOff2(){

    }

    public LiftOff2(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted: #" + id);
                return;
            }
        }
        System.out.println(status());
        Thread.yield();
    }
}
