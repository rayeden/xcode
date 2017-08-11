package chapter21_concurrency.blockingqueue;

/**
 * Created by xhtc on 2017/8/11.
 */

import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * 使用两个单独的组装线来创建涂油花生黄油和果冻的吐司三明治
 */

class Toast2 {

    public enum Status {DRY, PEANUT_BUTTER, JELLY, USED}

    private int id;

    public int getId() {
        return id;
    }

    private Status status = Status.DRY;

    public Toast2(int id) {
        this.id = id;
    }

    public void peanutButter() {
        this.status = Status.PEANUT_BUTTER;
    }

    public void jelly() {
        this.status = Status.JELLY;
    }

    public void used(){
        this.status = Status.USED;
    }

    public String toString() {
        return "Toast2, id: " + id + ", Status: " + status;
    }
}

class Sandwich {

    private Toast2 peanutButterToast;
    private Toast2 jelliedToast;

    private int id;

    public Sandwich(int id) {
        this.id = id;
    }

    public Sandwich(Toast2 peanutButterToast, Toast2 jelliedToast, int id) {
        this.peanutButterToast = peanutButterToast;
        this.jelliedToast = jelliedToast;
        this.id = id;
    }

    public String toString(){
        return "MakingSandwich(id: " + id + ")," +
                " combined with peanutButterToast(id:" + peanutButterToast.getId() + "), jelliedToast(id: " + jelliedToast.getId() + ")";
    }
}

class MakingToast implements Runnable {

    private BlockingQueue<Toast2> toastQueue;

    public MakingToast(BlockingQueue<Toast2> toastQueue) {
        this.toastQueue = toastQueue;
    }
    private int count = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(500);
                Toast2 toast2 = new Toast2(count++);
                print(toast2);
                toastQueue.put(toast2);
            }
        } catch (InterruptedException e) {
            print("making toast, interrupted");
        }

    }
}

//上花生黄油
class PeanutButter implements Runnable {

    private BlockingQueue<Toast2> peanutButterQueue;
    private BlockingQueue<Toast2> toastQueue;

    public PeanutButter(BlockingQueue<Toast2> toastQueue, BlockingQueue<Toast2> peanutButterQueue) {
        this.toastQueue = toastQueue;
        this.peanutButterQueue = peanutButterQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(500);
                //从吐司队列里取出一个
                Toast2 toast2 = toastQueue.take();
                //抹上花生黄油~
                toast2.peanutButter();
                print(toast2);
                //放到花生黄油的队列里
                peanutButterQueue.put(toast2);

            }
        } catch (InterruptedException e) {
            print("peanut butter toast, interrupted");
        }
    }
}

//上果冻
class Jellied implements Runnable {

    private BlockingQueue<Toast2> jelliedQueue;
    private BlockingQueue<Toast2> toastQueue;

    public Jellied(BlockingQueue<Toast2> toastQueue, BlockingQueue<Toast2> jelliedQueue) {
        this.jelliedQueue = jelliedQueue;
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(500);
                Toast2 toast2 = toastQueue.take();
                toast2.jelly();
                print(toast2);
                jelliedQueue.put(toast2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MakingSandwich implements Runnable {

    private BlockingQueue<Toast2> peanutButterQueue;
    private BlockingQueue<Toast2> jelliedQueue;

    private BlockingQueue<Sandwich> sandwichQueue;
    private static int count = 0;

    public MakingSandwich(BlockingQueue<Toast2> peanutButterQueue, BlockingQueue<Toast2> jelliedQueue) {
        this.peanutButterQueue = peanutButterQueue;
        this.jelliedQueue = jelliedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(500);
                Toast2 peanutButterToast = peanutButterQueue.poll();
                Toast2 jelliedToast = jelliedQueue.poll();

                Sandwich sandwich = new Sandwich(peanutButterToast, jelliedToast, count++);
                peanutButterToast.used();
                jelliedToast.used();
                print(sandwich);
                sandwichQueue.put(sandwich);
            }
        } catch (InterruptedException e) {
            print("making sandwich, interrupted");
        }
    }
}

public class E29_ToastOMatic2 {

    private static BlockingQueue<Toast2> toastQueue = new LinkedBlockingDeque<>();
    private static BlockingQueue<Toast2> peanutButterQueue = new LinkedBlockingDeque<>();
    private static BlockingQueue<Toast2> jelliedQueue = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new MakingToast(toastQueue));
        exec.execute(new PeanutButter(toastQueue, peanutButterQueue));
        exec.execute(new Jellied(toastQueue, jelliedQueue));
        //抛空指针异常？
        exec.execute(new MakingSandwich(peanutButterQueue, jelliedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
