package chapter21_concurrency.interrupted;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 同一个互斥可以被一个任务多次获得
 * StackOverFlowError?
 */
public class MultiLock {

    public synchronized void f1(int count){
        if(count-- > 0){
            print("f1() calling f2() with count " + count);
        }
        f2(count);
    }

    public synchronized void f2(int count){
        if(count-- > 0){
            print("f2() calling f1() with count " + count);
        }
        f1(count);
    }

    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(){
            public void run(){
                multiLock.f1(10);
            }
        }.start();
    }
}
