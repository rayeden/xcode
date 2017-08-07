package chapter21_concurrency.exercise;

import java.util.concurrent.*;

/**
 * Created by BG289522 on 2017/8/7.
 */

public class E10_ThreadMethod4Fibonacci {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadMethod4Fibonacci method = new ThreadMethod4Fibonacci("t");
        System.out.println(method.runTask(10));

    }

}

class ThreadMethod4Fibonacci {

    private String name;

    public ThreadMethod4Fibonacci(String name) {
        this.name = name;
    }

    private int fibonacce(int n) {
        if (n < 2)
            return 1;
        return fibonacce(n - 1) + fibonacce(n - 2);
    }

    public Integer runTask(final Integer count) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Integer result = exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return fibonacce(count);
            }
        }).get();
        exec.shutdown();
        return result;
    }

}
