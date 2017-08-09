package chapter21_concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/9.
 */
public class E17_Rays {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Sensor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Sensor.cancel();
        exec.shutdownNow();
        int sum = 0;
        for(Sensor sensor : Sensor.getSensorList()){
            sum += sensor.getNumber();
        }
        System.out.println("sum = " + sum);
        System.out.println("count = " + Counter.getCount());
    }

}

//传感器
class Sensor implements Runnable {

    private static List<Sensor> sensorList = new ArrayList<>();

    private int number;

    private int id;

    public static List<Sensor> getSensorList() {
        return sensorList;
    }

    private static boolean cancel;

    public int getNumber() {
        return number;
    }

    public Sensor(int id) {
        sensorList.add(this);
        this.id = id;
        cancel = false;
        number = 0;
    }

    public static void cancel() {
        cancel = true;
    }

    @Override
    public void run() {
        while (!cancel) {
            synchronized (this) {
                ++number;
            }
            System.out.println(id  + ": number: " + number + ", total: " + Counter.getCount());
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
            Counter.increment();
        }

    }
}

class Counter {

    private static int count = 0;

    public static synchronized int getCount() {
        return count;
    }

    //有synchronized修饰误差较小，但并不准确
    public static synchronized void increment() {
        count++;
        Thread.yield();
    }

}