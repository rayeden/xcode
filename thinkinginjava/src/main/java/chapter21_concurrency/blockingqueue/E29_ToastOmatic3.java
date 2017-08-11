package chapter21_concurrency.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 */

class Toast3 {
    public enum Status {
        DRY,
        BUTTERED,
        JAMMED,
        READY {
            public String toString() {
                return
                        BUTTERED.toString() + " & " + JAMMED.toString();
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast3(int idn) {
        id = idn;
    }

    public void butter() {
        status =
                (status == Status.DRY) ? Status.BUTTERED :
                        Status.READY;
    }

    public void jam() {
        status =
                (status == Status.DRY) ? Status.JAMMED :
                        Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class Alternator implements Runnable {
    private ToastQueue inQueue, out1Queue, out2Queue;
    private boolean outTo2; // control alternation
    public Alternator(ToastQueue in, ToastQueue out1,
                      ToastQueue out2) {
        inQueue = in;
        out1Queue = out1;
        out2Queue = out2;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
// Blocks until next piece of toast is available:
                Toast t = inQueue.take();
                if(!outTo2)
                    out1Queue.put(t);
                else
                    out2Queue.put(t);
                outTo2 = !outTo2; // change state for next time
            }
        } catch(InterruptedException e) {
            print("Alternator interrupted");
        }
        print("Alternator off");
    }
}

class Merger implements Runnable {
    private ToastQueue in1Queue, in2Queue, toBeButteredQueue,
            toBeJammedQueue, finishedQueue;
    public Merger(ToastQueue in1, ToastQueue in2,
                  ToastQueue toBeButtered, ToastQueue toBeJammed,
                  ToastQueue finished) {
        in1Queue = in1;
        in2Queue = in2;
        toBeButteredQueue = toBeButtered;
        toBeJammedQueue = toBeJammed;
        finishedQueue = finished;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = null;
                while(t == null) {
                    t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
                    if(t != null)
                        break;
                    t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
                }
// Relay toast onto the proper queue
                switch(t.getStatus()) {
                    case BUTTERED:
                        toBeJammedQueue.put(t);
                        break;
                    case JAMMED:
                        toBeButteredQueue.put(t);
                        break;
                    default:
                        finishedQueue.put(t);
                }
            }
        } catch(InterruptedException e) {
            print("Merger interrupted");
        }
        print("Merger off");
    }
}

public class E29_ToastOmatic3 {
    public static void main(String[] args) throws Exception {
        ToastQueue
                dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                toBeButteredQueue = new ToastQueue(),
                jammedQueue = new ToastQueue(),
                toBeJammedQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
        exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, toBeJammedQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
