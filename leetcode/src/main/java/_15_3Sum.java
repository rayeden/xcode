import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zetrov on 2016-12-03.
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 */
public class _15_3Sum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums2 = {};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public static List<List<Integer>> threeSum(int[] nums){
        ArrayList<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len-2; ++i){
            //去重
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int tmp = 0 - nums[i];
            int front = i+1;
            int rear = len-1;
            while(front < rear){
                if(nums[front] + nums[rear] < tmp)
                    front++;
                else if(nums[front] + nums[rear] > tmp)
                    rear--;
                else {
                    lists.add(Arrays.asList(nums[i], nums[front],nums[rear]));
                    //去重
                    while(front < rear && nums[front] == nums[front+1])
                        front++;
                    while(front < rear && nums[rear] == nums[rear-1])
                        rear--;
                    front++;
                    rear--;
                }
            }
        }
        return lists;
    }
}
