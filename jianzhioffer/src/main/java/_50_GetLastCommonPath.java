import datastruct.TreeNode;

import java.util.ArrayList;

/**
 * Created by zetrov on 2016/12/4.
 *
 * 找到二叉树中两个结点的最低公共祖先
 *
 */
public class _50_GetLastCommonPath {

    public static void solution(TreeNode a, TreeNode b){

    }


    //获取树的根结点到两个结点的路径，然后转换成两条单链表的公共结点问题
    public static boolean findPath(TreeNode node, int val, ArrayList<TreeNode> path){
        if(node.val == val)
            return true;
        path.add(node);
        boolean find = false;

        if(node.left != null)
            find = findPath(node.left, val, path);
        if(!find && node.right != null)
            find = findPath(node.right, val, path);
        if(!find)
            path.remove(path.size()-1);

        return find;
    }
}
