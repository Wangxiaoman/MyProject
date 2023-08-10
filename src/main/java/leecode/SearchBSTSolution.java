package leecode;

public class SearchBSTSolution {
    /**
     * @param root: the tree
     * @param val: the val which should be find
     * @return: the node
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return null;
        }
        if(root.val == val) {
            return root;
        }
        TreeNode tl = searchBST(root.left, val);
        TreeNode tr = searchBST(root.right, val);
        
        if(tl != null) return tl;
        if(tr != null) return tr;
        
        return null;
    }
    
    private static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
