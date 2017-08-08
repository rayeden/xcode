package chapter1_basic;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/8.
 */

/**
 * 在运行环境中配置文件参数，然后在控制台输入需要查找的数g
 */
public class BInarySearch {

    public static int rank(int key, int[] a) {
        int low = 0;
        int high = a.length;

        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while(!StdIn.isEmpty()){
            int key = StdIn.readInt();
            int index = rank(key, whitelist);
            if(index == -1){
                StdOut.println("key: " + key);
            } else {
                StdOut.println("index: " + index);
            }
            break;
        }
    }

}
