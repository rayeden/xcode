package chapter1_basic;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/8.
 */

/**
 * 在运行环境中配置文件参数，然后在控制台输入需要查找的数
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

    //递归
    public static int rank2(int key, int[] a) {
        return recursion(a, 0, a.length - 1, key);
    }

    public static int recursion(int[] a, int low, int high, int key) {
        if (low > high)
            return -1;
        int mid = low + (high - low) / 2;
        if (a[mid] == key)
            return mid;
        else if (a[mid] < key) {
            return recursion(a, mid + 1, high, key);
        } else {
            return recursion(a, low, mid - 1, key);
        }
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int index = rank2(key, whitelist);
            if (index == -1) {
                StdOut.println("key: " + key);
            } else {
                StdOut.println("index: " + index);
            }
            break;
        }
    }

}
