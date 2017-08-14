package chapter2;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/14.
 */
public class MergeSort {

    public static void mergeSort(int[] array, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        //数组多1，放置哨兵
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            left[i] = array[p + i];
        }
        left[n1] = Integer.MAX_VALUE;
        for (int j = 0; j < n2; j++) {
            right[j] = array[q + j];
        }
        right[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        //执行r-p+1次操作
        for (int k = 0; k < r - p + 1; k++) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
        }
    }

    public static void recMerge(int[] array, int p, int r) {
        if (p < r) {
            int q = (r + p) / 2;
            //left
            recMerge(array, p, q);
            //right
            recMerge(array, q + 1, r);
            mergeSort(array, p, q, r);
        }
    }


    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 5, 7, 1, 2, 3, 6};
        recMerge(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
