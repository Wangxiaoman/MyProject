package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinDiffInBSTSolution {
    /**
     * @param root: a Binary Search Tree (BST) with the root node
     * @return: the minimum difference
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        addNodeValue(root, nums);
        Collections.sort(nums);
        int sub = Integer.MAX_VALUE;
        for(int i=1;i<nums.size();i++) {
            int s = nums.get(i) - nums.get(i-1);
            if(s < sub) {
                sub = s;
            }
        }
        return sub;
    }
    
    private void addNodeValue(TreeNode n, List<Integer> nums) {
        if(n != null) {
            nums.add(n.val);
            addNodeValue(n.left, nums);
            addNodeValue(n.right, nums);
        }  else {
            return;
        }
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
