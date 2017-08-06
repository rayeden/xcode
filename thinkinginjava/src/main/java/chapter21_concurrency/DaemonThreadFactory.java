package chapter21_concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by xhtc on 2017/8/6.
 */

/**
 * 设置为后台线程的线程工厂
 */
public class DaemonThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }

}
