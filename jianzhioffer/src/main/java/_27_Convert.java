import datastruct.BinaryTree;
import datastruct.TreeNode;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Zetrov on 2016-12-31.
 * 10 6 4 -1 -1 8 -1 -1 14 12 -1 -1 16 -1 -1
 * 5 4 3 2 1 -1 -1 -1 -1 -1 -1
 */

public class _27_Convert {

    public static void main(String[] args) {
        int[] array = {10, 6, 4, -1, -1, 8, -1, -1, 14, 12, -1, -1, 16, -1, -1};
        int[] array2 = {5, 4, 3, 2, 1, -1, -1, -1, -1, -1, -1};
        Scanner sc = new Scanner(System.in);
        TreeNode tree = BinaryTree.buildBinaryTree(sc);
        BinaryTree.preOrder(tree);
        System.out.println();
        BinaryTree.inOrder(tree);
        System.out.println();

        TreeNode convert = convert2(tree);
        TreeNode tmp = convert;
        while (tmp != null) {
            System.out.print(tmp.val + " ");
            tmp = tmp.right;
        }
        System.out.println();
    }

    //pass
    public static TreeNode convert2(TreeNode pRootOfTree){
        if(pRootOfTree == null)
            return null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode pre = null;
        while(pRootOfTree != null || !stack.empty()){
            //把当前节点入栈直到遍历到最左边的节点
            while(pRootOfTree != null){
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            pRootOfTree = stack.pop();
            pRootOfTree.left = pre;
            if(pre != null)
                pre.right = pRootOfTree;
            pre = pRootOfTree;
            pRootOfTree = pRootOfTree.right;
        }
        while(pre.left != null)
            pre = pre.left;
        return pre;
    }
}
