package leecode;

public class BinaryTreeMaxDepth {
	public static int maxDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
		
        if(root.left != null || root.right != null){
        	return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
        }else{
        	return 1;
        }
    }
	
	public static void main(String[] args) {
		
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}