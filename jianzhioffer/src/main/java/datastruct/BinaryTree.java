package datastruct;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Zetrov on 2016-12-31.
 * 
 */

public class BinaryTree {

    public static void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //非递归中序遍历
    public static void inOrder(TreeNode root){
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        }
    }

    //非递归先序遍历
    public static void preOrder2(TreeNode root){
        if(root == null)
            return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null || !stack.isEmpty()) {
            while (root != null) {
                System.out.print(root.val + " ");
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }

    //非递归后续遍历
    public static void postOrder(TreeNode root){

    }

    public static TreeNode buildBinaryTree(Scanner sc) {
        TreeNode root = null;
        if(sc.hasNextInt()) {
            int val = sc.nextInt();
            if (val == -1)
                return null;
            root = new TreeNode(val);
            root.left = buildBinaryTree(sc);
            root.right = buildBinaryTree(sc);
        }
        return root;
    }
}
