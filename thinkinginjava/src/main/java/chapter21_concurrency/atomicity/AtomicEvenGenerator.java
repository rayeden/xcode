package chapter21_concurrency.atomicity;

import chapter21_concurrency.sharedresources.EvenChecker;
import chapter21_concurrency.sharedresources.IntGenerator;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xhtc on 2017/8/8.
 */
public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                EvenChecker.test(new AtomicEvenGenerator());
            }
        },5000);

    }
}
