/**
 * Created by Zetrov on 2017-02-11.
 *
 * 连续子数组的最大和，动态规划
 */
public class _31_FindGreatestSumOfSubArray {

    public static void main(String[] args) {
        int[] array = {1,-2,3,10,-4,7,2,-5};
        System.out.println(findGreatestSumOfSubArray(array));
    }

    public static int findGreatestSumOfSubArray(int[] array){
        int len = array.length;
        int dp[] = new int[len];
        dp[0] = array[0];
        int max = dp[0];
        for(int i = 1; i < len; ++i){
            if(dp[i-1] > 0){
                dp[i] = dp[i-1] + array[i];
            }else{
                dp[i] = array[i];
            }
            if(max < dp[i])
                max = dp[i];
        }
        return max;
    }
}
