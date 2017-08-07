package chapter21_concurrency.basicthreading;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * 可能会先打印出"Waiting for LiftOff!"
 * 因为是另起一个线程执行LiftOff，这并不影响main线程继续往下执行，
 * 所以在thread线程没有执行完，main线程就先完成了。
 */
public class BasicThreads {

    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Waiting for LiftOff!");
    }
}
