package chapter21_concurrency.blockingqueue;

/**
 * Created by xhtc on 2017/8/11.
 */

import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * 用BlockingQueue代替Piped
 * 有时候会读到null？
 */

class Sender2 implements Runnable {

    private BlockingQueue<Character> blockingQueue = new LinkedBlockingQueue<>();

    public BlockingQueue<Character> getBlockingQueue() {
        return blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (char i = 'a'; i < 'z'; i++) {
                    blockingQueue.put(i);
                    TimeUnit.MICROSECONDS.sleep(200);
                }
            }
        } catch (InterruptedException e) {
            print("Sender2 interrupted ");
        }
    }
}

class Receiver2 implements Runnable {

    private Sender2 sender2;

    public Receiver2(Sender2 sender2) {
        this.sender2 = sender2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                TimeUnit.MICROSECONDS.sleep(200);
                System.out.println("Read: " + sender2.getBlockingQueue().poll());
            }
        }catch (InterruptedException e){
            print("Receiver2 interrupted");
        }
    }
}

public class E30_BlockingQueuePipedIO {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Sender2 sender2 = new Sender2();
        Receiver2 receiver2 = new Receiver2(sender2);
        exec.execute(sender2);
        exec.execute(receiver2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
