package chapter21_concurrency.interrupted;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 花园委员会希望了解每天通过多个大门进入公园的总人数，每个大门一个计时器（任务）
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        //五个门，每个门一个计数器
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance(i));
        // Run for a while, then stop and collect the data:
        //主线程休眠3秒
        TimeUnit.SECONDS.sleep(5);
        //3秒后停止公园所有大门的计数器
        Entrance.cancel();
        exec.shutdown();
        //当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false，表示还有任务没有结束
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        //线程终止后，所有线程数据都被保存在静态的Entrance列表中，这些数据依然可以被拿出来访问
        print("Total: " + Entrance.getTotalCount());
        print("Sum of Entrances: " + Entrance.sumEntrances());
    }
}

class Count {
    private int count = 0;
    private Random rand = new Random(47);

    // 此处如果没有synchronized，结果就不正确
    public synchronized int increment() {
        int temp = count;
        //随机让步？
        //此处的temp和yield增加了同步失败的可能性
        if (rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    //本地维护一个number变量，提供对count对象的双重检查，以保证其记录的参观者数量是正确的
    private int number = 0;
    //构造函数注入id，不需要synchronized
    private final int id;
    //只会被读取和赋值（不会被其他域组合在一起被读取），所以不需要同步访问，就可以安全地进行操作
    //不放心也可以用synchronized进行同步
    private static volatile boolean canceled = false;

    //volatile域上的原子操作
    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        //把线程都放在entrances列表里统一管理
        //同时防止线程被垃圾回收
        entrances.add(this);
    }

    //增加number和count
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        //停止的时候还可能进来一两个人~
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}
