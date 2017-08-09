package chapter21_concurrency.interrupted;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 关闭任务在其上发生阻塞的底层资源 -- 中断I/O操作
 */
public class CloseResource {

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        //在本地8080端口上创建一个服务
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));
        TimeUnit.MICROSECONDS.sleep(100);
        print("Shutting down all thread");
        //shutdownNow()会发送一个interrupt()调用给它启动的所有线程
        exec.shutdownNow();
        TimeUnit.MICROSECONDS.sleep(1);
        print("Closing " + socketInput.getClass().getName());
        //关闭线程使用的底层资源
        socketInput.close();
        TimeUnit.MICROSECONDS.sleep(1);
        print("Closing " + System.in.getClass().getName());
        //关闭线程使用的底层资源
        System.in.close();

    }
}
