package chapter21_concurrency.interrupted;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 被阻塞的NIO通道会自动地响应中断 -- 中断I/O操作
 */
public class NIOInterruption{

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost",8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        //可以直接调用shutdownNow()给线程池中的所有线程发起中断
//        exec.shutdownNow();
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}


class NIOBlocked implements Runnable {

    private final SocketChannel sc;

    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            print("Waiting for read() in " + this);
            sc.read(ByteBuffer.allocate(1));
        } catch (IOException e) {
            print("ClosedByInterruptException in " + this);
        }
        print("Exiting NIOInterruption.run() " + this);
    }
}
