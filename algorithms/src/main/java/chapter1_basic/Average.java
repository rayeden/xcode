package chapter1_basic;

import edu.princeton.cs.algs4.*;

/**
 * Created by xhtc on 2017/8/9.
 */

/**
 * 控制台输入数字，计算平均值
 * ctrl + d／z结束
 */
public class Average {

    public static void main(String[] args) {

        double sum = 0.0;

        int cnt = 0;

        //控制台输入
        while(!StdIn.isEmpty()){
            sum += StdIn.readDouble();
            cnt ++;
        }

        double avg =sum / cnt;

        StdOut.printf("Average is %.5f\n", avg);

    }

}
