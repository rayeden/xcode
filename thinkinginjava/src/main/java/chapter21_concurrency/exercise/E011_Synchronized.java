package chapter21_concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 使用synchronized关键字控制对临界资源的访问
 * 并不是完全被控制，还是会出现冲突的情况？
 * 但是比没有synchronized关键字要好很多
 *
 */

//a = 10, b = 0
//a = 10, b = 0
//a = 8, b = 2
//a = 7, b = 3
//a = 6, b = 4

public class E011_Synchronized implements Runnable {

    private static int a = 10;

    private static int b = 0;

    @Override
    //    public void run(){
    public synchronized void run() {
        if (a > b) {
            System.out.println("a = " + a + ", b = " + b);
        }
        a--;
        b++;
        Thread.yield();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new E011_Synchronized());
        }
        exec.shutdown();
    }
}

