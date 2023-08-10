package leecode;

import java.util.Arrays;

public class TwoSumLessThanTargetSolution {
    /**
     * @param nums: An array of integer
     * @param target: An integer
     * @return: The sum of two numbers smaller than target
     */
    public static int twoSumLessThanTarget(int[] nums, int target) {
        // write your code here
        Arrays.sort(nums);
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int n = nums[i] + nums[j];
                if (n < target) {
                    result = Math.max(result, n);
                } else {
                    break;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(twoSumLessThanTarget(new int[] {2, 7, 11, 15}, 24));
    }
}
