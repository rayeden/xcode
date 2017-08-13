package chapter2.exercise;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/13.
 */

/**
 * 把n位二进制整数相加，存在一个长度为n + 1的数组里
 */
public class E04_BinarySum {

    public static int[] binarySum(int[] a, int[] b, int n) {
        int[] sum = new int[n + 1];
        boolean increment = false;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == b[i]) {
                //考虑两个二进制位都为1
                if (a[i] == 1) {
                    sum[i] = increment ? 1 : 0;
                    increment = true;
                } else if (a[i] == 0) {
                    sum[i] = increment ? 1 : 0;
                    increment = false;
                }
            } else {
                //一个为1，一个为0
                sum[i] = increment ? 0 : 1;
                increment = (sum[i] == 0);
            }
        }
        for (int i = n; i > 0; i--) {
            sum[i] = sum[ i- 1];
        }
        sum[0] = increment ? 1 : 0;
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {1,0,1};
        int[] b = {1,1,0};
        int[] sum = binarySum(a, b, 3);
        System.out.println(Arrays.toString(sum));
    }
}
