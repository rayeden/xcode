package chapter4;

/**
 * Created by Zetrov on 2016-12-25.
 */

/**
 * 这是一个线程安全类，因为此类的域,x和y都是不可变值，可以被自由地共享与发布
 */
public class Point {

    public final int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
