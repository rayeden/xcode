package chapter21_concurrency;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/6.
 */

/**
 * 后台线程不执行finally
 */
public class DaemonsDontRunFinally {

    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        //不是后台线程可以打印内容
        t.setDaemon(true);
        t.start();
    }
}

class ADaemon implements Runnable {

    @Override
    public void run() {
        try {
            print("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            print("Exiting  via InterruptedException");
        } finally {
            print("This should always run?");
        }
    }
}
