package executors;

/**
 * Created by xhtc on 2017/8/5.
 */

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool可以一次性预先执行线程分配，使用有限的线程集来执行任务
 */
public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        //线程池优先执行前五个任务，释放一个后再执行一个新任务
        for (int i = 0; i < 10; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
