package chapter21_concurrency.exercise;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/10.
 */
public class E21_WaitAndNotify {

    public static void main(String[] args) throws InterruptedException {
        CommonObject object = new CommonObject();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Thread1(object));
        exec.execute(new Thread2(object));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}

class CommonObject {

    private boolean commonObject = false;

    public boolean isCommonObject() {
        return commonObject;
    }

    public synchronized void setTrue(){
        System.out.println("true notifyAll");
        commonObject = true;
        notifyAll();
    }

    public synchronized void setFalse(){
        System.out.println("false notifyAll");
        commonObject = false;
        notifyAll();
    }

    public synchronized void waitWhileTrue() throws InterruptedException {
        if(commonObject){
            System.out.println("true wait");
            wait();
        }
    }

    public synchronized void waitWhileFalse() throws InterruptedException {
        if(!commonObject){
            System.out.println("false wait");
            wait();
        }
    }

}

class Thread1 implements Runnable {

    private CommonObject commonObject;

    public Thread1(CommonObject commonObject){
        this.commonObject = commonObject;
    }

    @Override
    public void run() {
        commonObject.setTrue();
        try {
            commonObject.waitWhileTrue();
            TimeUnit.MICROSECONDS.sleep(200);
        } catch (InterruptedException e) {
            print("commonObject waiting while true is interrupted, thread1");
        }
    }

}

class Thread2 implements Runnable {

    private CommonObject commonObject;

    public Thread2(CommonObject commonObject){
        this.commonObject = commonObject;
    }

    @Override
    public void run() {
        commonObject.setFalse();
        try {
            commonObject.waitWhileFalse();
            TimeUnit.MICROSECONDS.sleep(200);
        } catch (InterruptedException e) {
            print("commonObject waiting while false is interrupted, thread2");
        }
    }
}