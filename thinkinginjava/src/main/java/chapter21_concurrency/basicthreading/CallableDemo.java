package chapter21_concurrency.basicthreading;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * submit返回结果用Future保存，get()方法可以获取，或者用isDone()方法检查是否完成
 * 如果isDone()未完成就调用get()，此时get()会阻塞
 */
public class CallableDemo {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //保存所有线程的返回
            results.add(exec.submit(new TaskWithResult(i)));
//            exec.submit(new TaskWithResult(i)).isDone();
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
                return;
            } finally {
                exec.shutdown();
            }
        }
    }
}
