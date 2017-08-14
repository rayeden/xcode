package chapter2.exercise;

import java.util.Arrays;

/**
 * Created by xhtc on 2017/8/14.
 */
public class E2_1_SelectSort {

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int j = i, t = i;
            for (; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    t = j;
                }
            }
            array[t] = array[i];
            array[i] = min;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 4, 6, 1, 3};
        selectSort(array);
        System.out.println(Arrays.toString(array));

    }
}
