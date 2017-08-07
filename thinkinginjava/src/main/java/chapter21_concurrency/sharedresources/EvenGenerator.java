package chapter21_concurrency.sharedresources;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 并发访问时，出现对统一变量未按正确方式访问的情况
 * 导致++ 操作只完成一次时，被另外一个线程调用了next()，检测到奇数的情况
 * 注：Java的单次++递增操作也不是线程安全的
 */
public class EvenGenerator extends IntGenerator {

    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++ currentEvenValue;
        Thread.yield();
        ++ currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
