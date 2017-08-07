package chapter21_concurrency;

/**
 * Created by xhtc on 2017/8/7.
 */

/**
 * 继承Thread方法
 */
public class SimpleThread extends Thread {

    private int countDown = 5;

    private static int threadCount = 0;

    public SimpleThread(){
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString(){
        return "#" + getName() + "(" + countDown + "), ";
    }

    public void run(){
        while (true){
            System.out.println(this);
            if(--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
