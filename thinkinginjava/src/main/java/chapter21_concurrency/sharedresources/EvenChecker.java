package chapter21_concurrency.sharedresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/7.
 */

public class EvenChecker implements Runnable {

    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val + " not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count){
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator, 10);
    }

}
