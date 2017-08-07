package chapter21_concurrency.features;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * 线程加入200ms休眠后，结果为顺序执行（任务在200ms内完成）
 */
public class SleepingTask extends LiftOff {

    //异常不能跨线程传回给main，只能在线程内部进行捕获
    public void run(){
        try{
            while (countDown-- > 0){
                System.out.println(status());
                //同等于 Thread.sleep(200);
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
