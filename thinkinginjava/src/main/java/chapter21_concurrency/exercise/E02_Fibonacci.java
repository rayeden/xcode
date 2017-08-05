package chapter21_concurrency.exercise;

import net.mindview.util.Generator;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/5.
 */
public class E02_Fibonacci implements Generator<Integer>, Runnable {

    private int count;

    private final int n;

    public E02_Fibonacci(int n) {
        this.n = n;
    }

    private int fib(int n) {
        if (n < 2)
            return n;
        return fib(n - 1) + fib(n - 2);
    }

    @Override
    public void run() {
        Integer[] sequence = new Integer[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = next();
        }
        System.out.println("Seq. of " + n + ": " + Arrays.toString(sequence));
    }

    @Override
    public Integer next() {
        return fib(count ++);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new E02_Fibonacci(i)).start();
        }
    }
}
