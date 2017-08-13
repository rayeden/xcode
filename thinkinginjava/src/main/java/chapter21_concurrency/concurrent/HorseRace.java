package chapter21_concurrency.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/12.
 */

/**
 * 赛马场，把控制台调到只有马的几个线程，牛了个逼
 */
class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    //随机向前的步数
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public synchronized int getStrides(){
        return strides;
    }

    public String toString(){
        return "Horse " + id + " ";
    }

    public String tracks(){
        StringBuilder s = new StringBuilder();
        //获取随机步数，生成相应长度的字符串
        for (int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    //马向前的随机步数在3步之内
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    public HorseRace(int nHorses, final int pause){
        //作为匿名内部类，被提交到构造函数
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                //赛马场的距离
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                print(s);
                for (Horse horse : horses)
                    print(horse.tracks());
                for(Horse horse : horses){
                    //率先到达终点的马胜利
                    if(horse.getStrides() >= FINISH_LINE){
                        print(horse + " won!");
                        exec.shutdownNow();
                        return;
                    }
                    try{
                        TimeUnit.MILLISECONDS.sleep(pause);
                    } catch (InterruptedException e) {
                        print("barrier-action sleep interrupted");
                    }
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 20;
        new HorseRace(nHorses, pause);
    }
}
