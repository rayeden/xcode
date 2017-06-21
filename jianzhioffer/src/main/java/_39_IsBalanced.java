import datastruct.TreeNode;

/**
 * Created by Zetrov on 2017-02-11.
 */
public class _39_IsBalanced {

    //从下到上，后序遍历二叉树
    public boolean IsBalanced_Solution2(TreeNode root, int depth){
        if(root == null){
            depth = 0;
            return true;
        }
        int left = 0, right = 0;
        if(IsBalanced_Solution2(root.left, left) && IsBalanced_Solution2(root.right, right)){
            if(Math.abs(left-right) < 1){
                depth = left > right ? left + 1 : right + 1;
                return true;
            }
        }
        return false;
    }



    //从上到下遍历二叉树
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null)
            return true;

        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        if(Math.abs(left-right) > 1)
            return false;

        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    public int treeDepth(TreeNode root){
        if(root == null)
            return 0;
        else{
            int left = treeDepth(root.left);
            int right = treeDepth(root.right);

            return left > right ? left + 1 : right + 1;
        }
    }

}
