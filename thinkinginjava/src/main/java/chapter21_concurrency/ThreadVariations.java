package chapter21_concurrency;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by BG289522 on 2017/8/7.
 */

/**
 * 使用内部类来将线程代码隐藏在类中
 */

public class ThreadVariations {

    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

class InnerThread1 {

    private int countDown = 5;

    private Inner inner;

    //扩展自Thread的匿名内部类
    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0)
                        return;
                    sleep(10);
                }
            } catch (InterruptedException e) {
                print("Interrupted");
            }
        }

        public String toString() {
            return getName() + ": " + countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

class InnerThread2 {

    private int countDown = 5;

    private Thread t;

    //在构造器中创建匿名的Thread子类
    public InnerThread2(String name) {
        t = new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0)
                            return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return getName() + ": " + countDown;
            }
        };
        t.start();
    }
}

class InnerRunnable1 {
    private int countDown = 5;

    private Inner inner;

    private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
        }

        @Override
        public void run() {
            while (true) {
                print(this);
                if (--countDown == 0)
                    return;
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }
        }

        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

class InnerRunnable2 {
    private int countDown = 5;

    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print(this);
                    if (--countDown == 0)
                        return;
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        print("sleep() interrupted");
                    }
                }
            }
        }, name);
        t.start();
    }
}

class ThreadMethod {

    private int countDown = 5;

    private Thread t;

    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    while (true) {
                        try {
                            print(this);
                            if (--countDown == 0)
                                return;
                            sleep(10);
                        } catch (InterruptedException e) {
                            print("sleep interrupted");
                        }
                    }
                }
            };
            t.start();
        }
    }
}


