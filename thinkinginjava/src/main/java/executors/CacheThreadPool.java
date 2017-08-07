package executors;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * CachedThreadPool在程序执行的过程中通常会创建与所需数量相同的线程，
 * 然后在她回收旧线程时停止创建新线程
 */
public class CacheThreadPool {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }

}
