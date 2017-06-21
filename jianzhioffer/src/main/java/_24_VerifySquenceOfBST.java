/**
 * Created by Zetrov on 2016-12-29.
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */

public class _24_VerifySquenceOfBST {

    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int[] sequence2 = {4,8,6,12,16,14,10};
        int[] sequence3 = {};
        System.out.println(verifySquenceOfBST(sequence));
        System.out.println(verifySquenceOfBST(sequence2));
        System.out.println(verifySquenceOfBST(sequence3));
    }

    public static boolean verifySquenceOfBST(int[] sequence) {
       int len = sequence.length;
       if(len == 0)
           return false;
       return SequenceOfBST(sequence);
    }

    public static boolean SequenceOfBST(int[] sequence){
        int len = sequence.length;
        if(len >= 0 && len <= 2)
            return true;
        int root = sequence[len - 1];
        int mid = 0;
        for (int i = 0; i < len - 1; ++i) {
            mid = i;
            if (sequence[i] > root)
                break;
        }
        mid++;
        int[] left = new int[mid];
        int[] right = new int[len - mid - 1];
        int k = 0;
        for (int i = 0; i < len - 1; ++i) {
            if (i < mid)
                left[i] = sequence[i];
            else {
                if (sequence[i] < root)
                    return false;
                right[k++] = sequence[i];
            }
        }
        return SequenceOfBST(left) && SequenceOfBST(right);
    }
}
