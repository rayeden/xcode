package chapter21_concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/**
 * Created by xhtc on 2017/8/10.
 */

/**
 * 用Lock，Condition代替原有的wait()和notifyAll()
 */
public class E27_Restaurant2 {

    public static void main(String[] args) {
        new Restaurant2();
    }
}

class WaitPerson2 implements Runnable {
    private Restaurant2 restaurant;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public WaitPerson2(Restaurant2 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal == null)
                        condition.await();
                } finally {
                    lock.unlock();
                }
                print("Waitperson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef2 implements Runnable {
    private Restaurant2 restaurant;
    private int count;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Chef2(Restaurant2 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal != null)
                        condition.await();
                } finally {
                    lock.unlock();
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal2(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class Restaurant2 {
    Meal2 meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson2 waitPerson = new WaitPerson2(this);
    Chef2 chef = new Chef2(this);

    public Restaurant2() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
}


class Meal2 {
    private final int orderNum;

    public Meal2(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

