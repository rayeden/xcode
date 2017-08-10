package chapter21_concurrency.waitandnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by xhtc on 2017/8/10.
 */

/**
 * 生产者消费者模型
 * 一个生产者，一个消费者，一份meal
 */
class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) //刚开始没有meal，服务员等待
                        wait();
                }
                //服务员获取meal
                print("Waitperson got " + restaurant.meal);
                //此时餐厅内的meal又为空，服务员唤醒厨师继续下厨
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) //如果餐厅里有meal，那么厨师就不会开工
                        wait(); // ... for the meal to be taken
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    //此时中断线程，仍然可以再唤醒服务员线程？
                    //shutdownNow()方法向线程池中的线程发送中断请求，在方法中抛出InterruptedException
                    //因此chef的run()方法会执行到sleep()方法抛出InterruptedException(),中断此线程
                    restaurant.exec.shutdownNow();
                }
                //呼叫服务员取餐
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    //此时waitPerson线程为MONITOR状态
                    restaurant.waitPerson.notifyAll();
                }
                //如果此处不调用sleep方法，就不能从这里捕获由调用interrupted()抛出的InterruptedException
                //程序会返回到while检查中断状态，然后结束线程，此时不会打印"Chef interrupted"
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    final WaitPerson waitPerson = new WaitPerson(this);
    final Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
