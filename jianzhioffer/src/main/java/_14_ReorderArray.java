import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zetrov on 2016-12-26.
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */

public class _14_ReorderArray {

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }

    public static void reOrderArray(int [] array) {
        if(array == null)
            return;
        int low = 0;
        int len = array.length;

        //先用一个数组保存所有偶数
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < len; ++i){
            //偶数
            if((array[i] & 1) == 0)
                list.add(array[i]);
        }

        while(low < len){
            //跳过所有奇数，使low指向遇到的第一个偶数
            while(low < len && (array[low] & 1) == 1)
                low++;
            int high = low+1;
            //如果有偶数，跳过所有偶数，得到第一个奇数
            while(high < len && (array[high] & 1) == 0)
                high++;
            //把奇数交换到low的位置，使奇数逐个连续
            if(high < len){
                array[low] ^= array[high];
                array[high] ^= array[low];
                array[low] ^= array[high];
            }
            low++;
        }

        //把事先保存的偶数数组复制到所有奇数后面
        int i = len - list.size();
        for(int k = 0; k < list.size(); ++k){
            array[i++] = list.get(k);
        }
    }
}
