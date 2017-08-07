package chapter21_concurrency;

import static net.mindview.util.Print.print;

/**
 * Created by BG289522 on 2017/8/7.
 */

/**
 * 如果Sleeper被中断或者结束，Joiner会和Sleeper一起结束
 */
public class Joining {

    public static void main(String[] args) {
        //启动Sleepy和Grumpy两个线程，休眠1500毫秒
        Sleeper sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy",1500);
        //Sleepy加入dopey线程，Grumpy加入doc线程
        //在Joiner里调用Sleeper.join()
        Joiner dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}

class Sleeper extends Thread {

    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            //异常被捕获时会清理interrupt()标志，因此总是为false
            print(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has wakened");
    }
}

class Joiner extends Thread {

    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try{
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}
