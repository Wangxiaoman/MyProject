package leecode;

import java.util.ArrayList;
import java.util.List;


public class InvertBinaryTree {
	
	public static TreeNode invertTree(TreeNode root) {
        if(root == null){
        	return null;
        }else{
        	TreeNode temp = invertTree(root.left);
        	root.left = invertTree(root.right);
        	root.right = temp;

        	return root;
        }
    }
	
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void preOrder(TreeNode root){
		if(root == null){
			return;
		}else{
			preOrder(root.left);
			list.add(root.val);
			preOrder(root.right);
		}
	}
	
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode l = new TreeNode(2);
		root.left = l;
		TreeNode r = new TreeNode(3);
		root.right = r;
		
		preOrder(root);
		for(int i : list){
			System.out.print(i+",");
		}
		
		System.out.println();
		
		TreeNode revertTree = invertTree(root);
		list.clear();
		preOrder(revertTree);
		for(int i : list){
			System.out.print(i+",");
		}
	}
}
