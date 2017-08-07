package chapter21_concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 如果异常被抛出线程方法
 */
public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        //在代码块中添加try - catch无法捕获从run方法抛出的异常
        try {
            //直接创建线程池，无法捕获线程抛出的异常
//            ExecutorService exec = Executors.newCachedThreadPool();

            //给线程设置异常处理器，就可以捕获线程抛出的异常
            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            exec.shutdown();
        }catch (RuntimeException e){
            print("catch exception");
        }
    }
}
