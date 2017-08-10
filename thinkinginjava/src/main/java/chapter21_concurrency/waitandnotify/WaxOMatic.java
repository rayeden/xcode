package chapter21_concurrency.waitandnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/10.
 */

/**
 * 用wait()挂起并且释放锁，完成多线程之间的协作
 * With Sequence
 */
class Car {

    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        //循环检查waxOn条件，判断是否挂起
        while (!waxOn){
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        //循环检查waxOn条件，判断是否挂起
        while (waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {

    private Car car;

    public WaxOn(Car c){
        this.car = c;
    }

    @Override
    public void run() {
        try {
            //检查中断
            while (!Thread.interrupted()) {
                //上蜡
                print("Wax on! ");
                //模拟涂蜡的时间为200ms
                TimeUnit.MICROSECONDS.sleep(200);
                car.waxed();
                //挂起，释放car的对象锁
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt, waxOn");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {

    private Car car;

    public WaxOff(Car c){
        car = c;
    }

    @Override
    public void run() {
        try {
            //等待上蜡，线程挂起
            car.waitForWaxing();
            print("Wax off!");
            TimeUnit.MICROSECONDS.sleep(200);
            //抛光
            car.buffed();
        } catch (InterruptedException e) {
            print("Exiting via interrupt, waxOff");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        //主线程休眠1秒后，中断线程池内所有线程
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
