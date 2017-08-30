package chapter4_IoC.classloader_reflect;

/**
 * Created by xhtc on 2017/8/30.
 */
public class PrivateCar {

    private String color;

    protected void drive(){
        System.out.println("drive private car! The color is: " + color);
    }
}
