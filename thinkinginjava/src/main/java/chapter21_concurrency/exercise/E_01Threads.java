package chapter21_concurrency.exercise;

/**
 * Created by xhtc on 2017/8/5.
 */
public class E_01Threads {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyThread(3));
            thread.start();
        }
    }

}
