package chapter21_concurrency.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xhtc on 2017/8/8.
 */

/**
 * JDK1.7 并没有获得奇数然后输出
 */
public class AtomicityTest implements Runnable {

    private static int i = 0;

    public int getValue(){
        return i;
    }

    private void evenIncrement(){
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true){
            int val = at.getValue();
            if(val % 2 != 0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
