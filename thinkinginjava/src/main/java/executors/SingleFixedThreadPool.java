package executors;

import chapter21_concurrency.basicthreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * 相当于限制数量为1的FixedThreadPool
 */
public class SingleFixedThreadPool {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }

}
