package chapter21_concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 */
public class Philosopher implements Runnable{

    private Chopstick left;

    private Chopstick right;

    private int ponderFactor;

    private Random rand = new Random(47);

    private final int id;

    public Philosopher(int id) {
        this.id = id;
    }

    private void pause() throws InterruptedException {
        if(ponderFactor == 0)
            return;
        TimeUnit.MICROSECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    //哲学家不断思考吃饭，拿起筷子放下筷子
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                print(this + " " + "thinking");
                pause();
                print(this + " " + "grabbing right");
                right.taken();
                print(this + " " + "grabbing left");
                left.taken();
                print(this + " " + "eating");
                pause();
                right.drop();
                left.drop();
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    public String toString(){
        return "Philosopher " + id;
    }
}
