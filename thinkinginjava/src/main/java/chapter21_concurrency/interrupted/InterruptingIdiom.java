package chapter21_concurrency.interrupted;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 检查中断，需设置运行参数
 * 参数为10时，在中途中断了线程的运行，只清理了第一个资源
 */

//被设计用来相应interrupt()的类，使用时必须紧跟try-finally字句，确保它的状态保持一致
class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        print("NeedsCleanup " + id);
    }

    public void cleanup() {
        print("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            //检查中断状态
            while (!Thread.interrupted()) {
                // 在创建的NeedsCleanup资源后都必须紧跟try-finally字句，确保cleanup()方法总是会被调用
                NeedsCleanup n1 = new NeedsCleanup(1);
                try {
                    print("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    //第二个NeedsCleanup资源
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    try {
                        print("Calculating");
                        for (int i = 1; i < 2500000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        print("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            print("Exiting via while() test");
        } catch (InterruptedException e) {
            print("Exiting via InterruptedException");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            print("usage: java InterruptingIdiom delay-in-mS");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }
}