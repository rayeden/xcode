package chapter21_concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/10.
 */

public class E22_BusyWait {

    public static void main(String[] args) throws InterruptedException {
        Signal signal = new Signal();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new BusyWatcher(signal));
        exec.execute(new BusyWaitTask(signal));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}

class Signal {

    private boolean signal = false;

    public synchronized boolean isSignal() {
        return signal;
    }


    public synchronized void setSignal(boolean signal) {
        this.signal = signal;
    }
}

class BusyWaitTask implements Runnable {

    private Signal signal;

    public BusyWaitTask(Signal signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MICROSECONDS.sleep(200);
                synchronized (this) {
                    if(!signal.isSignal()) {
                        signal.setSignal(true);
                        System.out.println("BusyWaitTask set signal to TRUE");
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusyWaitTask is interrupted");
        }
    }

}

class BusyWatcher implements Runnable {

    private Signal signal;

    public BusyWatcher(Signal signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        //忙等
        try {
            while (true) {
                synchronized (this) {
                    TimeUnit.MICROSECONDS.sleep(100);
                    if (signal.isSignal()) {
                        System.out.println("BusyWatcher set signal to FALSE");
                        signal.setSignal(false);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("BusyWatcher is interrupted");
        }
    }
}
