package chapter2;

/**
 * Created by xhtc on 2017/8/11.
 */

/**
 * 插入排序（扑克手牌）
 */
public class InsertSort {

    public static void insertSort(int[] array) {
        if (array.length <= 1)
            return;
        for (int i = 1; i < array.length; i++) {
            //记录当前元素
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key){
                array[j+1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 4, 6, 1, 3};
        insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }
}
