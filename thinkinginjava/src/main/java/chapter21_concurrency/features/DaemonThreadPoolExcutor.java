package chapter21_concurrency.features;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/6.
 */
public class DaemonThreadPoolExcutor extends ThreadPoolExecutor{


    public DaemonThreadPoolExcutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
    }

}
