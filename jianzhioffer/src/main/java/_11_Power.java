/**
 * Created by Zetrov on 2016-12-26.
 */
public class _11_Power {

    public static void main(String[] args) {
        System.out.println(power(2,-3));
    }

    public static double power(double base, int exponent) {

        if(base == 0){
            if(exponent == 0)
                return 1.0;
            else if(exponent < 0)
                return -1;
        }
        int sign = 1;
        if(exponent < 0) {
            sign = -1;
            exponent = -exponent;
        }

        double res = 1.0;
        for(int i = 0; i < exponent; ++i){
            res *= base;
        }

        System.out.println(res);

        if(sign == -1)
            res = 1.0/res;

        return res;
    }
}
