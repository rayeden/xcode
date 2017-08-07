package chapter21_concurrency.exercise;

/**
 * Created by BG289522 on 2017/8/7.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 习题解答参考，用同步锁控制线程池
 */
public class E10_FibonacciSum2 {

    public static void main(String[] args) {
        List<Future<Integer>> results = new ArrayList<>();
        //初始化线程池
        FibonacciSum2.init();
        for (int i = 1; i <= 5; i++) {
            results.add(FibonacciSum2.runTask(i));
        }
        Thread.yield();
        FibonacciSum2.shutdown();
        for(Future<Integer> fi : results){
            try{
                System.out.println(fi.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class FibonacciSum2 {

    private static ExecutorService exec;

    private static int fib(int n){
        if(n < 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static synchronized Future<Integer> runTask(final int n){
        assert exec != null;
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += fib(i);
                }
                return sum;
            }
        });
    }

    public static synchronized void init(){
        if(exec == null)
            exec = Executors.newCachedThreadPool();
    }

    public static synchronized void shutdown(){
        if(exec != null)
            exec.shutdown();
        exec = null;
    }
}
