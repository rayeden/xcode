package chapter21_concurrency.exercise;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/5.
 */

public class E03_Executors {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            exec.execute(new LiftOff());
        }
        //务必在完成任务后关闭线程池
        exec.shutdown();
    }

}
