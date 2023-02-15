package leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class BinaryTreeMaxWidth {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode l1 = new TreeNode(3);
		TreeNode r1 = new TreeNode(2);
		root.left = l1;
		root.right = r1;

		TreeNode ll1 = new TreeNode(5);
		TreeNode lr1 = new TreeNode(3);
		l1.left = ll1;
		l1.right = lr1;

		TreeNode rr1 = new TreeNode(9);
		r1.right = rr1;

		System.out.println(widthOfBinaryTree(root));
		System.out.println(widthOfBinaryTree2(root));
		System.out.println(getMax(root));
	}

	public static int widthOfBinaryTree(TreeNode root) {
		List<TreeNode> levelNodes = new ArrayList<>();
		levelNodes.add(root);
		int max = 1;
		while(true) {
			levelNodes = getNextLevelNodeWidth(levelNodes);
			if(levelNodes != null) {
				int width = getLevelWidth(levelNodes);
				max = width > max ? width : max;
			} else {
				return max;
			}
		}
	}

	public static int getLevelWidth(List<TreeNode> treeNodes) {
		if (CollectionUtils.isEmpty(treeNodes)) {
			return -1;
		}
		int begin = -1;
		int end = -1;

		int size = treeNodes.size();
		for (int i = 0; i < size; i++) {
			if (begin < 0 && treeNodes.get(i) != null) {
				begin = i;
			}
			if (end < 0 && treeNodes.get(size - i - 1) != null) {
				end = size - i;
			}
		}

		return end - begin;
	}

	public static List<TreeNode> getNextLevelNodeWidth(List<TreeNode> levelTreeNodes) {
		if (levelTreeNodes == null) {
			return null;
		}
		List<TreeNode> nodeList = new ArrayList<>();
		boolean levelNotNull = false;
		for (TreeNode tn : levelTreeNodes) {
			if (tn != null) {
				if (tn.left != null || tn.right != null) {
					levelNotNull = true;
				}
				nodeList.add(tn.left);
				nodeList.add(tn.right);
			} else {
				nodeList.add(null);
				nodeList.add(null);
			}
		}
		if (levelNotNull) {
			return nodeList;
		} 
		return null;
	}
	
	public static int widthOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 1;
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res = Math.max(queue.peekLast().val - queue.peekFirst().val + 1, res);
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    cur.left.val = cur.val * 2 - 1;
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = cur.val * 2;
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
	
	
	public static int getMax(TreeNode node) {
		if(node == null) {
			return 0;
		}
		int result = 1;
		node.val = 1;
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offer(node);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			result = Math.max(queue.peekLast().val-queue.peekFirst().val + 1, result);
			while(size -- > 0) {
				TreeNode currentNode = queue.poll();
				if(currentNode.left != null) {
					currentNode.left.val = currentNode.val * 2 - 1;
					queue.offer(currentNode.left);
				}
				if(currentNode.right != null) {
					currentNode.right.val = currentNode.val * 2;
					queue.offer(currentNode.right);
				}
			}
		}
		return result;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

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
