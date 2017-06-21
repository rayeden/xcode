import datastruct.BinaryTree;
import datastruct.TreeNode;

/**
 * Created by zetrov on 2016/12/4.
 *
 * 给出二叉树中序和先序序列，还原二叉树
 */
public class _6_ConstructBinaryTree___ {

    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};

        TreeNode tree = solution(pre, 0, 8, in, 0, 8);
        BinaryTree.preOrder(tree);
    }


    public static TreeNode solution(int[] pre, int ps, int pe, int[] in, int is, int ie){
        TreeNode node = null;
        if(ps < pe){
            int num = pre[ps];
            int index = 0;
            for (int i = is; i < ie; i++) {
                if(in[i] == num){
                    index = i;
                    break;
                }
            }
            node = new TreeNode(num);
            node.left = solution(pre, ps+1, ps+index-is, in, is, index);
            node.right = solution(pre, ps+index-is+1, pe, in, index+1, ie);
        }
       return node;
    }
}
