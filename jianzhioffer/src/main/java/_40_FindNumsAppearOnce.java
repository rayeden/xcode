/**
 * Created by Zetrov on 2017-02-11.
 */
public class _40_FindNumsAppearOnce {

    public static void main(String[] args) {
        int[] array = {2,4,3,6,3,2,5,5};
        int[] nums1 = new int[1];
        int[] nums2 = new int[2];

        FindNumsAppearOnce(array, nums1, nums2);
        System.out.println(nums1[0] + " " + nums2[0]);
    }

    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array == null)
            return;
        int len = array.length;
        int tmp = array[0];
        for (int i = 1; i < len; i++) {
            tmp ^= array[i];
        }
        int first1Bit = getFirst1Bit(tmp);
        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < len; i++) {
            if(is1Bit(array[i], first1Bit)){
                num1[0] ^= array[i];
            }else{
                num2[0] ^= array[i];
            }
        }
    }

    public static int getFirst1Bit(int tmp){
        int index = 0;
        while((tmp & 1) == 0 && index < 32){
            tmp = tmp >> 1;
            index ++;
        }
        return index;
    }

    public static boolean is1Bit(int num, int index){
        num = num >> index;
        return (num & 1) == 1;
    }
}
