package leecode;

// 654. 最大二叉树
public class MaxTree {
    
    public static void main(String[] args) {
        TreeNode t = constructMaximumBinaryTree(new int[] {1,4,5,8,2});
        showNode(t);
    }
    
    private static void showNode(TreeNode t) {
        if(t != null) {
            System.out.println(t.val);
            if(t.left != null) {
                showNode(t.left);
            }
            if(t.right != null) {
                showNode(t.right);
            }
        }
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums, int b, int e) {
        if (b == e) {
            return null;
        }

        int maxIndex = getArrayMaxIndex(nums, b, e);
        int max = nums[maxIndex];

        TreeNode tn = new TreeNode(max);
        tn.left = constructMaximumBinaryTree(nums, b, maxIndex);
        tn.right = constructMaximumBinaryTree(nums, maxIndex+1, e);
        return tn;
    }

    private static int getArrayMaxIndex(int[] nums, int b, int e) {
        int max = -1;
        int index = 0;
        for (int i = b; i < e; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    static class TreeNode {
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
    
    
    public TreeNode maxTree(int[] nums, int l, int r){
        if(l > r){
            return null;
        }
        //bond为当前数组中最大值的索引
        int bond = findMax(nums, l, r);
        TreeNode root = new TreeNode(nums[bond]);
        root.left = maxTree(nums, l, bond - 1);
        root.right = maxTree(nums, bond + 1, r);
        return root;
    }
    //找最大值的索引
    public int findMax(int[] nums, int l, int r){
        int max = Integer.MIN_VALUE, maxIndex = l;
        for(int i = l; i <= r; i++){
            if(max < nums[i]){
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

