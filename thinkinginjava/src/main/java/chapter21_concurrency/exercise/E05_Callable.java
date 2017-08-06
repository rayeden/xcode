package chapter21_concurrency.exercise;

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xhtc on 2017/8/5.
 */

public class E05_Callable implements Generator<Integer>, Callable {

    //fibonacci数组数量
    private final int n;

    //fibonacci数字
    private int count;

    public E05_Callable(int n) {
        this.n = n;
    }

    private Integer Fibonacci(int n) {
        if (n < 2)
            return n;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    @Override
    public Integer next() {
        return Fibonacci(count++);
    }

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += next();
        }
        System.out.println("Sum of Seq. of " + n + ": " + sum);
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        int n = 5;
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new E05_Callable(i)));
        }
        Thread.yield();
        exec.shutdown();
        for (Future<Integer> fi : results) {
            System.out.println(fi.get());
        }
    }
}
