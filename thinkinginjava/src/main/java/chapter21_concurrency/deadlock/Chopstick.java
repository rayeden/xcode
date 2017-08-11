package chapter21_concurrency.deadlock;

/**
 * Created by xhtc on 2017/8/11.
 */
public class Chopstick {

    private boolean taken = false;

    public synchronized void taken() throws InterruptedException {
        //筷子被拿起时，需等待
        while (taken)
            wait();
        taken = true;
    }

    public synchronized void drop(){
        taken = false;
        notifyAll();
    }
}
