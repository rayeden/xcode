package chapter21_concurrency.concurrent;

/**
 * Created by xhtc on 2017/8/14.
 */
public class Fat {

    private volatile double d;

    private static int counter = 0;

    private final int id = counter++;

    //耗时的构造器
    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    public String toString(){
        return "Fat id: " + id;
    }

}
