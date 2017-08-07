package chapter21_concurrency.catchexception;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 *
 * JSE5的新接口，允许在每个Thread对象上都附着一个异常处理器
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    //捕获异常
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught: " + e);
    }
}
