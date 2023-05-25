package leecode;
/**
 * @author xiaomanwang
 * 给定一个数组 nums 以及一个整数 target 。
 *  你需要把数组中等于target的元素移动到数组的最前面，并且其余的元素相对顺序不变。
 *  你的所有移动操作都应该在原数组上面操作。
 */

public class MoveTargetSolution {
    /**
     * @param nums: a list of integer
     * @param target: an integer
     * @return: nothing
     */
    public static void moveTarget(int[] nums, int target) {
        // write your code here
        int[] result = new int[nums.length];
        int count = 0;
        for(int n : nums) {
            if(n == target) {
                count = count + 1;
            }
        }
        
        for (int i = 0; i < count; i++) {
            result[i] = target;
        }
        
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                continue;
            } else {
                result[count+j] = nums[i];
                j++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }
    }
    
    public static void main(String[] args) {
        moveTarget(new int[] {5, 1, 6, 1}, 1);
    }
}
