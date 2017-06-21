import datastruct.BinaryTree;
import datastruct.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by zetrov on 2017/2/12.
 *
 * 层次遍历二叉树
 */
public class _60_Print {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = BinaryTree.buildBinaryTree(sc);
        ArrayList<ArrayList<Integer>> lists = Print(root);

        for(int i = 0; i < lists.size(); ++i){
            System.out.println(lists.get(i).toString());
        }
        System.out.println();
    }

    //层次遍历
    public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        if(pRoot == null)
            return lists;
        Queue<TreeNode> level = new LinkedList<TreeNode>();
        Queue<TreeNode> curLevel = new LinkedList<TreeNode>();
        //把根节点加入到level列表
        level.add(pRoot);
        while(!level.isEmpty()){
            ArrayList<Integer> list = new ArrayList<Integer>();
            while(!level.isEmpty()){
                TreeNode node = level.poll();
                list.add(node.val);
                if(node.left != null)
                    curLevel.add(node.left);
                if(node.right != null)
                    curLevel.add(node.right);
            }
            int len = curLevel.size();
            for(int i = 0; i < len; ++i){
                level.add(curLevel.poll());
            }
            lists.add(list);
        }
        return lists;
    }
}
