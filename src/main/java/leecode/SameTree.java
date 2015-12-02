package leecode;

/**
 * 
 * @ClassName: SameTree
 * @Description: Given two binary trees, write a function to check if they are
 *               equal or not. Two binary trees are considered equal if they are
 *               structurally identical and the nodes have the same value.
 * @author xiaoman
 * @date 2015年12月2日 下午4:44:23
 * 
 */
public class SameTree {
	
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == q){
			return true;
		}
		
		if(q == null || p == null){
			return false;
		}
		
        if(p.val == q.val){
        	if(p.left == null && p.right == null && q.left == null && q.right == null){
        		return true;
        	}
        	
        	if((p.left == null && q.left != null)||(p.left != null && q.left == null)){
        		return false;
        	}
        	
        	if((p.right == null && q.right != null)||(p.right != null && q.right == null)){
        		return false;
        	}
        	
        	return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
        
        return false;
    }
	
	public static void main(String[] args) {
		TreeNode p = new TreeNode(1);
		TreeNode pr = new TreeNode(3);
		p.right = pr;
		
		
		TreeNode q = new TreeNode(1);
		TreeNode qr = new TreeNode(3);
		q.right = qr;
		
		System.out.println(isSameTree(p,q));
	}
}
