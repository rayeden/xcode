/**
 * Created by zetrov on 2016/12/4.
 *
 * 旋转数组的最小数字
 *
 * 考虑low,mid,high三个位置值一样的情况，用二分查找平均时间复杂度为O(logn)
 */
public class _8_MinInOrder {

    public static void main(String[] args) {
        int[] nums1 = {1,1,0,1,1};
        int[] nums2 = {3,4,5,1,2};
        int[] nums3 = {1,2,3,4,5};
        int[] nums4 = {0,1,1,1,1};
        int[] nums5 = {1,1,1,1,0};
        System.out.println(solution(nums1));
        System.out.println(solution(nums2));
        System.out.println(solution(nums3));
        System.out.println(solution(nums4));
        System.out.println(solution(nums5));
    }

    public static int solution(int[] nums){
        int len = nums.length-1;
        if(len == 0)
            return 0;
        if(len == 1)
            return nums[0];
        //如果第一个数小于最后一个数，第一个数就是最小值
        if(nums[0] < nums[len])
            return nums[0];
        int index = binaryFind3(nums, 0, len);
        return nums[index];
    }

    //通过
    public static int binaryFind3(int[] nums, int low, int high){
        int mid = (low + high) / 2;
        //如果中位数比其前一个数字小，那么该中位数就是最小值
        if(nums[mid] < nums[mid-1])
            return mid;
        //如果中位数比最后一个数字大，则要找的最小值在数组的后半段
        if(nums[mid] > nums[high])
            return binaryFind(nums, mid+1, high);
        //如果中位数比最后一个数字小，则要找的最小值在数组的前半段
        else if(nums[mid] < nums[high])
            return binaryFind(nums, low, mid+1);
        //如果中位数和最后一个数字相等，那么只能从low到high逐个遍历找到最小值
        else{
            int tmp = nums[low+1];
            for(int i = low+1; i < high; ++i){
                if(nums[i] < tmp)
                    tmp = nums[i];
            }
            return tmp;
        }
    }


    //未通过
    public static int binaryFind(int[] nums, int low, int high) {
        int pivot = nums[high];
        int mid = 0;
        while(low <= high){
            if(high-low == 1 && nums[low] > nums[high])
                return high;
            mid = low + (high - low) / 2;
            if(nums[low] == nums[high] && nums[low] == nums[mid]){
                for(int i = low; i < high; ++i){
                    if(nums[i] > nums[i+1]){
                        return i+1;
                    }
                }
            }
            else if(nums[mid] > pivot)
                low = mid+1;
            else if(nums[mid] <= pivot)
                high = mid-1;
        }
        return mid;
    }

    //未通过
    public static int binaryFind2(int[] nums, int low, int high){
        while(low < high){
            if(high - low == 1 && (nums[low] > nums[high]))
                return high;
            int mid = (low + high)/2;
            if(nums[mid] > nums[low]){
                low = mid;
            }else if(nums[mid] < nums[low])
                high = mid;
            else{
                while(low < high && nums[low] == nums[low+1])
                    low++;
                while(low < high && nums[high] == nums[high-1])
                    high--;
                low++;
                high--;
            }
        }
        return low;
    }
}
