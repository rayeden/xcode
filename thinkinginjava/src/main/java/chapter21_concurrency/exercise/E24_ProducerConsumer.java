package chapter21_concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xhtc on 2017/8/10.
 */

class FlowQueue<T> {

    private Queue<T> queue = new LinkedList<>();
    private int maxSize;

    public FlowQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void put(T v) throws InterruptedException {
        while (queue.size() >= maxSize){
            wait();
        }
        //当队列为空时，add方法会报错，offer方法会返回false
        queue.offer(v);
        maxSize++;
        notifyAll();
    }

    public synchronized T get() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        T returnVal = queue.poll();
        maxSize--;
        notifyAll();
        return returnVal;
    }
}

class Item{
    private static int counter;
    private int id = counter ++;
    public String toString(){
        return "Item " + id;
    }
}

class Producer implements Runnable{

    private int delay;

    private FlowQueue<Item> output;

    public Producer(FlowQueue<Item> output, int sleepTime){
        this.output = output;
        delay = sleepTime;
    }

    @Override
    public void run() {
        for (;;){
            try {
                output.put(new Item());
                TimeUnit.MICROSECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

class Consumer implements Runnable{

    private int delay;

    private FlowQueue<Item> input;

    public Consumer(FlowQueue<Item> input, int delay) {
        this.input = input;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (;;){
            try {
                System.out.println(input.get());
                TimeUnit.MICROSECONDS.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class E24_ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
//        if(args.length < 2){
//            System.err.println("Usage java E24_ProducerConsumer" + " <producer sleep time> <consumer sleep time>");
//            System.exit(1);
//        }
        int producerSleep = 100;
        int consumerSleep = 100;
        FlowQueue<Item> fq = new FlowQueue<>(100);
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new Producer(fq, producerSleep));
        exec.execute(new Consumer(fq, consumerSleep));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();

    }

}
