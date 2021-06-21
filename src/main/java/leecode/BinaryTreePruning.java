package leecode;

// 814. 二叉树剪枝
public class BinaryTreePruning {
    // 1,0,1,0,0,0,1
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(0);
        TreeNode r = new TreeNode(1);
        TreeNode ll = new TreeNode(0);
        TreeNode lr = new TreeNode(0);
        TreeNode rl = new TreeNode(0);
        TreeNode rr = new TreeNode(1);

        root.left = l;
        l.left = ll;
        l.right = lr;
        root.right = r;
        r.left =rl;
        r.right =rr;
        
        showTreeNode(root);
        System.out.println();
        System.out.println("-----------");
        TreeNode t =pruneTree2(root);
        showTreeNode(t);
        System.out.println();
        System.out.println("-----------");
        
        TreeNode r1 = new TreeNode(0);
        t = pruneTree2(r1);
        showTreeNode(t);
    }
    
    private static void showTreeNode(TreeNode root) {
        if(root == null) {
            System.out.println();
            return;
        }
        
        System.out.print(root.val +" ");
        if(root.left != null) {
            showTreeNode(root.left);
        }
        if(root.right != null) {
            showTreeNode(root.right);
        }
    }
    
    public static TreeNode pruneTree(TreeNode root) {
        TreeNode result = root;
        return pruneTree1(result);
    }
    
    public static TreeNode pruneTree2(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        root.right = pruneTree2(root.right);
        root.left = pruneTree2(root.left);
        
        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        
        return root;
    }
    
    public static TreeNode pruneTree1(TreeNode node) {
        
        if(node.left !=null) {
            if(node.left.val == 1) {
                node.left = pruneTree1(node.left);
            } else {
                if(node.left.left == null && node.left.right == null) {
                    node.left = null;
                } else {
                    node.left = pruneTree1(node.left);
                }
            }
        }
        
        if(node.right !=null) {
            if(node.right.val == 1) {
                node.right = pruneTree1(node.right);
            } else {
                if(node.right.left == null && node.right.right == null) {
                    node.right = null;
                } else {
                    node.right = pruneTree1(node.right);
                }
            }
        }
        
        if(node.left == null && node.right == null && node.val == 0) {
            return null;
        }
        return node;
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
