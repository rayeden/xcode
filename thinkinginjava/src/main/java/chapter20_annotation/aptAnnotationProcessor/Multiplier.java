package chapter20_annotation.aptAnnotationProcessor;

/**
 * Created by BG289522 on 2017/8/4.
 */

@ExtractInterface("IMultiplier")
public class Multiplier {

    public int multiply(int x, int y){
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args) {
        System.out.println("11 * 16 = " + new Multiplier().multiply(11,16));
    }
}
