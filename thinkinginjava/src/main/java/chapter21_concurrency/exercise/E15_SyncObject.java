package chapter21_concurrency.exercise;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/8.
 */

public class E15_SyncObject {

    public static void main(String[] args) throws InterruptedException {
        final SingleSynch singleSync = new SingleSynch();
        final TripleSynch tripleSync = new TripleSynch();
        print("Test SingleSynch...");
        Thread t1 = new Thread(){
            public void run(){
                singleSync.f();
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            public void run(){
                singleSync.g();
            }
        };
        t2.start();
        singleSync.h();
        t1.join();
        t2.join();
        print("Test TripleSynch...");
        new Thread(){
            public void run(){
                tripleSync.f();
            }
        }.start();
        new Thread(){
            public void run(){
                tripleSync.g();
            }
        }.start();
        tripleSync.h();
    }
}

class SingleSynch {
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g() {
        for (int i = 0; i < 5; i++) {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h() {
        for (int i = 0; i < 5; i++) {
            print("h()");
            Thread.yield();
        }
    }
}

class TripleSynch {

    private Object synObjectG = new Object();

    private Object synObjectH = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g() {
        for (int i = 0; i < 5; i++) {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h() {
        for (int i = 0; i < 5; i++) {
            print("h()");
            Thread.yield();
        }
    }
}


