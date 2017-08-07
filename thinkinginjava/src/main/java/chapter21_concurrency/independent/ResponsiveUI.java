package chapter21_concurrency.independent;

import java.io.IOException;

/**
 * Created by xhtc on 2017/8/7.
 */
public class ResponsiveUI extends Thread{

    private static volatile double d = 1;

    public ResponsiveUI(){
        setDaemon(true);
        start();
    }

    //把运算放在任务里单独进行，可以在进行运算的同时监听控制台的输入
    public void run(){
        while (true){
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
//        new UnresponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }

}

//计算和控制台输入在同一个线程里，无法同时监听
class UnresponsiveUI {

    private volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while (d > 0){
            d = d + (Math.PI + Math.E) / d;
        }
        System.in.read();
    }
}
