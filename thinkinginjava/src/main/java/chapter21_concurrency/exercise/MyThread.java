package chapter21_concurrency.exercise;

/**
 * Created by xhtc on 2017/8/5.
 */
public class MyThread implements Runnable {

    private int count = 3;

    private static int task = 0;

    private final int id = task ++;

    public MyThread(int count){
        this.count = count;
    }

    public MyThread() {
        System.out.println("MyThread #" + id);
    }

    @Override
    public void run() {
        while (count-- > 0) {
            System.out.println("This is my thread running. #" + id);
            Thread.yield();
        }
        System.out.println("end... #" + id);
    }
}
