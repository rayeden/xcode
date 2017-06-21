import java.util.ArrayList;

/**
 * Created by Zetrov on 2017-02-11.
 *
 * 和为S的连续正数序列
 */
public class _41_FindContinuousSequence {

    public static void main(String[] args) {
        int sum = 100;
        ArrayList<ArrayList<Integer>> lists = new  ArrayList<ArrayList<Integer>>();
        lists = FindContinuousSequence(sum);
        for(int i = 0; i < lists.size(); ++i){
            System.out.println(lists.get(i).toString());
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int midNum = sum / 2 + 2;
        int[] nums = new int[midNum];

        ArrayList<ArrayList<Integer>> lists = new  ArrayList<ArrayList<Integer>>();
        if(sum == 0 || sum == 1)
            return lists;
        for (int i = 0; i < midNum; i++) {
            nums[i] = i+1;
        }
        int low = 0, high = 1;
        int tmp = nums[low] + nums[high];
        while(low < high && high < midNum-1){
            if(tmp < sum){
                tmp += nums[++high];
            }else if(tmp > sum){
                tmp -= nums[low++];
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = low; i <= high ; i++) {
                    list.add(nums[i]);
                }
                lists.add(list);
                tmp += nums[++high];
            }
        }
        return lists;
    }
}
