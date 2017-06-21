package chapter15_generics.bound;

import java.awt.*;

/**
 * Created by xhtc on 2017/5/8.
 */

/**
 * 通过继承消除冗余
 */
interface HasColor{
    Color getColor();
}

class Colored<T extends Color>{

    private T item;
    Colored(T item){
        this.item = item;
    }

    T getItem(){
        return item;
    }

    Color color(){
        return color();
    }
}

class Dimension{
    public int x, y, z;
}

/**
 * 多边界，必须是类在前，接口在后
 * @param <T>
 */
class ColoredDimension<T extends Dimension & HasColor>{
    private T item;
    ColoredDimension(T item){
        this.item = item;
    }
    T getItem(){
        return item;
    }
    Color color(){
        return item.getColor();
    }
    int getX(){
        return item.x;
    }
    int getY(){
        return item.y;
    }
    int getZ(){
        return item.z;
    }
}

interface Weight{
    int wight();
}

class Solid<T extends Dimension & HasColor & Weight>{

    private T item;

    Solid(T item){
        this.item = item;
    }
    T getItem(){
        return item;
    }
    Color color(){
        return item.getColor();
    }
    int getX(){
        return item.x;
    }
    int getY(){
        return item.y;
    }
    int getZ(){
        return item.z;
    }
    int weight(){
        return item.wight();
    }
}

class Bounded extends Dimension implements HasColor, Weight {

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int wight() {
        return 0;
    }
}

public class BasicBounds {

    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<Bounded>(new Bounded());
        solid.color();
        solid.getY();
        solid.weight();
    }

}
