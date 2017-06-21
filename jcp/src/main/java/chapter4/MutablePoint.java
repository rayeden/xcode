package chapter4;

/**
 * Created by Zetrov on 2016-12-25.
 */
public class MutablePoint {

    public int x, y;

    public MutablePoint(){
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }
}
