package leecode;

import java.util.ArrayList;
import java.util.List;

// 144. 二叉树的前序遍历
public class BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode lr = new TreeNode(4);
        TreeNode r = new TreeNode(3);
        TreeNode rl = new TreeNode(5);
        root.left = l;
        root.right = r;
        l.right = lr;
        r.left = rl;
        
        System.out.println(new BinaryTreePreorderTraversal().preorderTraversal(root));
    }

    private List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        orderNode(root);
        return list;
    }

    private void orderNode(TreeNode root) {
        list.add(root.val);
        if (root.left != null) {
            orderNode(root.left);
        }
        if (root.right != null) {
            orderNode(root.right);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
