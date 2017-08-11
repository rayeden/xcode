package chapter21_concurrency.blockingqueue;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/11.
 * PipedReader和普通I/O之间最重要的差异：PipedReader是可中断的
 */

/**
 * 任务间使用管道进行输入/输出
 */

class Sender implements Runnable{

    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter(){
        return out;
    }

    @Override
    public void run() {
        try {
            while (true){
                //把数据写进管道
                for (char c = 'A'; c <= 'Z'; c++){
                    out.write(c);
                    TimeUnit.MICROSECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (InterruptedException e) {
            print(e + " Sender write exception");
        } catch (IOException e) {
            print(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable{

    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true){
                //从管道读取数据，当调用read()时没有数据，管道将会自动阻塞
                print("Read: " + (char)in.read() + ",");
            }
        } catch (IOException e) {
            print(e + " Receiver read exception");
        }
    }
}

public class PipedIO {

    public static void main(String[] args) throws InterruptedException, IOException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

}
