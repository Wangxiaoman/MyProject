package leecode;

/**
 * @author xiaomanwang
 * 给定一个非空整数数组 nums 表示学生每次考试的分数，返回一个等长的数组 res，
 * 对于 nums 中的每次考试分数 nums[i]，找到它后面最近更高的分数，
 * 若不存在，则保留原考试分数
 */
public class ProgressSolution {
    /**
     * @param nums: 
     * @return: return the nearest higher score
     */
    public int[] getProgress(int[] nums) {
        // write your code here
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]) {
                    temp = nums[j];
                    break;
                }
            }
            result[i] = temp;
        }
        return result;
    }
}
