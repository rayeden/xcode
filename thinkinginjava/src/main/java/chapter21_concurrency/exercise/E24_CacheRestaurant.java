package chapter21_concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/10.
 */

/**
 * 消费者生产者，增加缓冲区
 * 跑起来了~ 完美~
 */
public class E24_CacheRestaurant {

    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = new Restaurant();
        TimeUnit.SECONDS.sleep(3);
        restaurant.exec.shutdownNow();
    }
}

class Meal {

    private static int count = 0;

    private int id;

    public synchronized int getId() {
        return id;
    }

    public Meal() {
        id = count ++;
    }
}

class Chef implements Runnable {

    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    //如果缓冲区满
                    if (restaurant.isFull()) {
                        System.out.println("Status Full, Chef is waiting ...");
                        wait();
                    }
                }
                synchronized (restaurant.waiter) {
                    Meal meal = new Meal();
                    restaurant.meals.add(meal);
                    System.out.println("Chef is cooking... meal id: " + meal.getId());
                    restaurant.waiter.notifyAll();
                }
//                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef is interrupted");
        }
    }
}

class Waiter implements Runnable {

    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    //如果缓冲区为空，服务员线程等待
                    if (restaurant.isEmpty()) {
                        System.out.println("Status Empty, Waiter is waiting...");
                        wait(200);
                    }
                }
                synchronized (restaurant.chef) {
                    Meal meal = restaurant.meals.get(0);
                    System.out.println("consume meal, id: " + meal.getId());
                    //移除队列第一个
                    restaurant.meals.remove(0);
                    restaurant.chef.notifyAll();
                }
//                TimeUnit.MICROSECONDS.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("waiter is interrupted");
        }
    }
}

class Restaurant {
    //缓冲区
    final List<Meal> meals = new ArrayList<>();

    public synchronized boolean isFull() {
        if (meals.size() == 10)
            return true;
        else
            return false;
    }

    public synchronized boolean isEmpty() {
        if (meals == null || meals.size() == 0)
            return true;
        else
            return false;
    }

    final Chef chef = new Chef(this);
    final Waiter waiter = new Waiter(this);

    ExecutorService exec = Executors.newCachedThreadPool();

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waiter);
    }

}