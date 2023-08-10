package leecode;

import java.util.HashSet;
import java.util.Set;

public class MaxSubArraySolution {
    
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
        }
        
        int result = dp[0];
        for (int i = 1; i < dp.length; i++) {
            result = Math.min(result, dp[i]);
        }
        Set<Character> set = new HashSet<>();
        set.add('a');
        return result;
    }
}
