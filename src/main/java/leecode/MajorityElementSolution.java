package leecode;

import java.util.LinkedHashMap;
import java.util.Map;

public class MajorityElementSolution {
    public static boolean isMajorityElement(int[] nums, int target) {
        // write your code here
        int mid = nums[nums.length / 2];
        if (mid != target) {
            return false;
        }
        int bindex = beginIndex(nums, 0, nums.length, target);
        if (nums[bindex + mid] == target) {
            return true;
        }
        return false;
    }

    private static int beginIndex(int[] nums, int b, int e, int target) {
        int mid = (b + e) / 2;
        if (mid == 0 && nums[0] == target) {
            return 0;
        }
        if (nums[mid] == target && nums[mid - 1] < target) {
            return mid;
        }
        if (nums[mid] == target && nums[mid - 1] == target) {
            return beginIndex(nums, b, mid, target);
        }
        return beginIndex(nums, mid + 1, e, target);
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Write your code here
        Map<Integer, Integer> numMap = new LinkedHashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            numMap.put(nums2[i], i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int n = nums1[i];
            int index = numMap.get(n);
            boolean bigger = false;
            for (int j = index; j < nums2.length; j++) {
                if (nums2[j] > n) {
                    result[i] = nums2[j];
                    bigger = true;
                    break;
                }
            }
            if (!bigger) {
                result[i] = -1;
            }
        }
        return result;
    }

    public static int sportPlanPerformance(int[] plan, int k, int lower, int upper) {
        // write your code here
        int sum = 0;
        for (int i = 0; i <= (plan.length - k); i++) {
            int daySum = 0;
            for (int j = 0; j < k; j++) {
                daySum += plan[i+j]; 
            }
            if (daySum < lower) sum += -1;
            if (daySum > upper) sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        // System.out.println(isMajorityElement(new int[] {1,2,2,2,3,4}, 2));
        // System.out.println(isMajorityElement(new int[] {1,1,2,2,3,4}, 2));
        // System.out.println(isMajorityElement(new int[] {1,2,3,3,3,3,4,5}, 3));

        int[] r = nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2});
        for (int n : r) {
            System.out.println(n);
        }

//        int sr = sportPlanPerformance(new int[] {1, 3, 5}, 1, 3, 3);
        int sr = sportPlanPerformance(new int[] {1,2,1,1}, 2, 4, 6);
        System.out.println(sr);
        
    }
}
