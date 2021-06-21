package leecode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rr = new TreeNode(2);
        TreeNode rrl = new TreeNode(3);
        rr.left = rrl;
        root.right =rr;
        
        System.out.println(inorderTraversal(root));
    }
    private static List<Integer> result = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        orderTr(root);
        return result;
    }
    
    private static void orderTr(TreeNode node) {
        if(node == null) {
            return;
        }
        
        if(node.left != null) {
            orderTr(node.left);
        }
        
        result.add(node.val);
        
        if(node.right != null) {
            orderTr(node.right);
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


