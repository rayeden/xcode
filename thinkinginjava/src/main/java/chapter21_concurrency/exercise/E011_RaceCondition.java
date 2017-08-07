package chapter21_concurrency.exercise;

import chapter21_concurrency.catchexception.MyUncaughtExceptionHandler;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 坦克装弹，类似一个消费者生产者模型
 */
public class E011_RaceCondition {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
//        Tank tank = new Tank();
        //使用被synchronized修饰的线程安全的坦克类，不会抛出“不正确状态”的异常
        Tank tank = new SafeTank();
        for (int i = 0; i < 10; i++) {
            exec.execute(new ConsistencyChecker(tank));
        }
        Thread.yield();
        exec.shutdown();
    }
}

class ConsistencyChecker implements Runnable {

    private static Random rnd = new Random();

    private Tank tank;

    ConsistencyChecker(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void run() {
        for (; ; ) {
            if (rnd.nextBoolean())
                tank.fill();
            else
                tank.drain();
            tank.validate();
        }
    }
}

class SafeTank extends Tank {
    public synchronized void validate() {
        super.validate();
    }

    public synchronized void fill() {
        super.fill();
    }

    public synchronized void drain() {
        super.drain();
    }
}

class Tank {
    enum State {EMPTY, LOADED}

    private State state = State.EMPTY;
    private int current_load = 0;

    public void validate() {
        //状态为空，装弹；状态为满，不装弹。
        if ((state == State.EMPTY && current_load != 0) ||
                (state == State.LOADED && current_load == 0))
            throw new IllegalStateException();
    }

    public void fill() {
        state = State.LOADED;
        Thread.yield();
        current_load = 10;
    }

    public void drain() {
        state = State.EMPTY;
        Thread.yield();
        current_load = 0;
    }

}
