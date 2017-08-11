package chapter21_concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 */

/**
 * 把筷子放在筷笼里（用队列维护筷子）
 * 当只有2个哲学家时，没人取一只，没取到另外一只就会同时挂起进入死锁
 * 队列不能保证哲学家每次都取到两只筷子，如果筷子比哲学家多一只，就能解除死锁
 */
class Chopstick2{
    private final int id;

    private boolean taken;

    public Chopstick2(int ident){
        id = ident;
    }
    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }
    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
    public String toString(){
        return "Chopstick " + id;
    }
}

class Philosopher2 implements Runnable {

    private static Random rand = new Random(47);
    private ChopstickBin bin;
    private final int id;
    private final int ponderFactor;

    public Philosopher2(ChopstickBin bin, int id, int ponderFactor) {
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.bin = bin;
    }

    private void pause() throws InterruptedException {
        if(ponderFactor == 0)
            return;
        TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
                //从筷笼取出两只筷子
                Chopstick2 c1 = bin.get();
                print(this + " has " + c1 + " waiting for another one");
                Chopstick2 c2 = bin.get();
                print(this + " has " + c2);
                print(this + " eating");
                pause();
                //放回筷笼
                bin.put(c1);
                bin.put(c2);
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    public String toString(){
        return "Philosopher " + id;
    }
}

class ChopstickQueue extends LinkedBlockingQueue<Chopstick2>{
}

class ChopstickBin {
    private ChopstickQueue bin = new ChopstickQueue();
    public Chopstick2 get() throws InterruptedException {
        return bin.take();
    }
    public void put(Chopstick2 stick) throws InterruptedException {
        bin.put(stick);
    }
}

public class E31_DeadlockingDiningPhilosophers2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ChopstickBin bin = new ChopstickBin();
        int size = 3;
        int ponder = 2;
        for (int i = 0; i < size; i++) {
            bin.put(new Chopstick2(i));
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher2(bin, i, ponder));
        }
        TimeUnit.SECONDS.sleep(5);

        exec.shutdownNow();
    }
}
