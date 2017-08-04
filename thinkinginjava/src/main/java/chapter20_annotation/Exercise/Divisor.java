package chapter20_annotation.Exercise;

import chapter20_annotation.aptAnnotationProcessor.ExtractInterface;
import chapter20_annotation.aptAnnotationProcessor.Multiplier;

/**
 * Created by BG289522 on 2017/8/4.
 */

@ExtractInterface("IDivisor")
public class Divisor {

    public int divisor(int x, int y){
        int total = 0;
        while (x >= y){
            x = sub(x, y);
            total ++;
        }
        return total;
    }

    private int sub(int x, int y){
        return x - y;
    }

    public static void main(String[] args) {
        System.out.println("1000/12 = " + new Divisor().divisor(1000,12));
    }

}
