package chapter21_concurrency;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.printnb;

/**
 * Created by xhtc on 2017/8/6.
 */

/**
 * 后台线程创建的依然是后台线程
 */
public class Daemons {

    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        printnb("d.isDaemon() = " + d.isDaemon() + ", ");
        TimeUnit.SECONDS.sleep(1);
    }
}

class Daemon implements Runnable {

    private Thread[] t = new Thread[10];

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            //后台线程创建子线程
            t[i] = new Thread(new DaemonSpaun());
            t[i].start();
            printnb("DaemonSpawn " + i + " started. ");
        }
        for (int i = 0; i < t.length; i++) {
            printnb("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
        }
        while (true) {
            Thread.yield();
        }
    }
}


class DaemonSpaun implements Runnable {

    @Override
    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}