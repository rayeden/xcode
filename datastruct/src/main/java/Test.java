import java.util.Scanner;

/**
 * Created by zetrov on 2017/2/12.
 */
public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeNode root = BinaryTree.buildBinaryTree(sc);
        BinaryTree.inOrder(root);
        System.out.println();
        BinaryTree.preOrder2(root);
    }
}
