package leecode;

/**
 * @author xiaomanwang
 * 给定一个环形整数数组 nums，其长度为 n。
    对于所有下标 ，以每个下标为起始位置遍历所有数组元素，
    计算该环形数组的前缀和，请问有多少个下标可以使得该前缀和的所有元素均为非负数。
 */
public class PrefixSumSolution {
    /**
     * @param nums: 
     * @return: count of non-negative prefix sum
     */
    public static int getPrefixSum(int[] nums) {
        // write your code here
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < i + nums.length; j++) {
                int index = (j >= nums.length) ? j - nums.length : j;
                currentSum = currentSum + nums[index];
                if(currentSum < 0) {
                    count = count + 1;
                    break;
                }
            }
        }
        return nums.length - count;
    }
    
    public static void main(String[] args) {
        System.out.println(getPrefixSum(new int[] {-3,5,1,2}));
        System.out.println(getPrefixSum(new int[] {1,-1,1,-1,1,-1}));
    }
}
