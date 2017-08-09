package chapter21_concurrency.interrupted;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 原方法是用任务中的boolean值控制任务是否中断，本方法直接调用interrupt()中断线程
 */
public class E19_OrnamentalGarden2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance2(i));
        }
        TimeUnit.SECONDS.sleep(3);
        //所有线程调用interrupted()方法进行中断
        exec.shutdownNow();
        if(!exec.awaitTermination(250, TimeUnit.MICROSECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Entrance2.getTotalCount());
        print("Sum of Entrances: " + Entrance2.sumEntrances());
    }

}

class Entrance2 implements Runnable{

    private static Count count = new Count();

    private static List<Entrance2> entrance2List = new ArrayList<>();

    private int number;

    private final int id;

    Entrance2(int id) {
        this.id = id;
        entrance2List.add(this);
    }

    public static int getTotalCount(){
        return count.value();
    }

    public static int sumEntrances(){
        int sum = 0;
        for (Entrance2 entrance2 : entrance2List){
            sum += entrance2.number;
        }
        return sum;
    }

    public synchronized int getValue(){
        return number;
    }

    public String toString(){
        return "Entrance " + id + ": " + getValue();
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                ++ number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("Stopping " + this);
                return;
            }
        }
    }
}
