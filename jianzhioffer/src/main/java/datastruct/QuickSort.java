package datastruct;

/**
 * Created by zetrov on 2016/12/4.
 */

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {4,3,10,2,5,3,7,5,3,4,6,5};
        int from = 0;
        int to = nums.length-1;
        quickSort(nums, from, to);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void quickSort(int[] nums, int from, int to){
        //partition部分
        if(from < to){
            //i始终指向from前一个元素
            int i = from - 1;
            int pivot = nums[to];
            for (int j = from; j < to; ++j){
                //如果遍历的元素=pivot也要进行交换
                if(nums[j] <= pivot){
                    ++i;
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            nums[to] = nums[i+1];
            nums[i+1] = pivot;
            //递归部分
            quickSort(nums, from, i);
            quickSort(nums, i+1, to);
        }
    }

    public static int partition(int[] nums, int from, int to){
        int i = from - 1;
        if(from < to){
            //i始终指向from前一个元素
            int pivot = nums[to];
            for (int j = from; j < to; ++j){
                //如果遍历的元素=pivot也要进行交换
                if(nums[j] <= pivot){
                    ++i;
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
            nums[to] = nums[i+1];
            nums[i+1] = pivot;
        }
        return i+1;
    }

    public static void quickSort2(int[] nums, int from, int to){
        if(from == to)
            return;
        int index = partition(nums, from, to);
        if(index > from)
            quickSort(nums, from, index-1);
        if(index < to)
            quickSort(nums, index+1, to);
    }
}
