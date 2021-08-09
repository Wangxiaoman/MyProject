package leecode;

// 101. 对称二叉树
// 给定一个二叉树，检查它是否是镜像对称的。
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return cmp(root.left, root.right);
    }

    public boolean cmp(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return cmp(left.left, right.right) && cmp(left.right, right.left);
    }

    public static class TreeNode {
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

    public static void main(String[] args) {

        TreeNode ll = new TreeNode(3);
        TreeNode lr = new TreeNode(4);
        TreeNode l = new TreeNode(2, ll, lr);

        TreeNode rl = new TreeNode(4);
        TreeNode rr = new TreeNode(3);
        TreeNode r = new TreeNode(2, rl, rr);
        TreeNode root = new TreeNode(1, l, r);


        SymmetricTree st = new SymmetricTree();
        // st.getNodeValues(root);
//        System.out.println(st.values);

        System.out.println(st.isSymmetric(root));
    }
}
