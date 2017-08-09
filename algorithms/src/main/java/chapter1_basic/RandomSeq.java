package chapter1_basic;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 运行参数： 5 100.0 200.0
 * 生成100.0到200.0的五个随机数，保留两位小数
 */
public class RandomSeq {

    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);

        double lo = Double.parseDouble(args[1]);

        double hi = Double.parseDouble(args[2]);

        for (int i = 0; i < N; i++) {

            double x = StdRandom.uniform(lo, hi);

            StdOut.printf("%.2f\n", x);

        }
    }
}
