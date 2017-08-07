package chapter21_concurrency.sharedresources;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 给方法加锁，就不会再出现currentEvenValue只递增一次就被其他线程访问的情况（出现奇数）
 */
public class SynchronizedEvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++ currentEvenValue;
        Thread.yield();
        ++ currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
