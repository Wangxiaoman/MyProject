package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 * 
 * @author xiaomanwang
 *
 */
public class BinarySearchTreeToGreaterSumTree {

	static int pre = 0;

	public static TreeNode bstToGst(TreeNode root) {
		if (root.right != null)
			bstToGst(root.right);
		pre = root.val = pre + root.val;
		if (root.left != null)
			bstToGst(root.left);
		return root;

	}

	public static void getNode(List<Integer> input, List<Integer> result, TreeNode root) {
		if (root == null) {
			return;
		}
		int value = 0;
		for (int i = 0; i < input.size(); i++) {
			if (root.val <= input.get(i)) {
				value += input.get(i);
			}
		}
		root.val = value;
		result.add(value);

		if (root.left != null) {
			getNode(input, result, root.left);
		}

		if (root.right != null) {
			getNode(input, result, root.right);
		}
	}

	public static void getNodeValue(List<Integer> result, TreeNode root) {
		if (root == null) {
			return;
		}
		result.add(root.val);

		if (root.left != null) {
			getNodeValue(result, root.left);
		}

		if (root.right != null) {
			getNodeValue(result, root.right);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode left = new TreeNode(2);
		root.left = left;
		TreeNode right = new TreeNode(5);
		root.right = right;
		TreeNode ll = new TreeNode(1);
		right.left = ll;
		List<Integer> r = new ArrayList<>();
		getNodeValue(r, root);
		System.out.println(r);
//		List<Integer> nr = new ArrayList<>();
//		getNode(r, nr, root);
//		System.out.println(nr);
		
		bstToGst(root);
		
		List<Integer> rr = new ArrayList<>();
		getNodeValue(rr, root);
		System.out.println(rr);
		
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
