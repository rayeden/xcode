package chapter21_concurrency.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by xhtc on 2017/8/14.
 */

/**
 * 模拟一个对象池管理
 * （对象池技术，比如数据库连接池，线程池）
 * 对象池的优点: 复用池中对象, 没有分配内存和创建堆中对象的开销, 没有释放内存和销毁堆中对象的开销, 进而减少垃圾收集器的负担, 避免内存抖动;
 * 不必重复初始化对象状态, 对于比较耗时的constructor和finalize来说非常合适;
 * @param <T>
 */
class CheckOutTask<T> implements Runnable {

    private static int counter = 0;

    private final int id = counter++;

    private Pool<T> pool;

    public CheckOutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            print(this + "checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            print(this + "checking in " + item);
            pool.checkIn(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return "CheckoutTask " + id + " ";
    }
}

public class SemaphoreDemo {

    final static int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        //Fat类的对象池
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckOutTask<>(pool));
        }
        print("All CheckoutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            printnb(i + ": main() thread checked out ");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    print("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        print("Checking in objects in " + list);
        for (Fat f : list)
            pool.checkIn(f);
        //冗余的签入操作会被Pool忽略
        for (Fat f: list)
            pool.checkIn(f);
        exec.shutdown();
    }

}
