package chapter21_concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/6.
 */

/**
 * 创建一定数量的线程，每个线程sleep不同的随机秒数
 */
public class E06_SleepTask implements Runnable{

    private static int count = 0;

    private int id = count++;

    private int round = 10;

    @Override
    public void run() {
        try {
            int time = new Random().nextInt(round);
            TimeUnit.SECONDS.sleep(time);
            System.out.println("sleep time: " + time + " , #" + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new E06_SleepTask());
        }
        exec.shutdown();
    }
}
