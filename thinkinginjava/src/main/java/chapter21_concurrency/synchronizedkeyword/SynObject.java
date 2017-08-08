package chapter21_concurrency.synchronizedkeyword;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/8.
 */

/**
 * 对不同的方法/对象上锁，互不影响
 */
class DualSynch {

    private Object syncObject = new Object();

    //synchronized同步整个方法
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        //同步代码块
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }
}

public class SynObject {

    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(){
            public void run(){
                ds.f();
            }
        }.start();
        ds.g();
    }
}
