package leecode;

import java.util.Arrays;

// 最接近的三数之和
// input: nums = [-1,2,1,-4], target = 1
// output: 2
public class ThreeSumClosest {
    public static void main(String[] args) {
//        System.out.println(getSubCloset(new int[] {1, 3, 5, 7}, 0, 3, 4));
        System.out.println(threeSumClosest(new int[] {-1,2,1,-4}, 1));
    }
    
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            
            while(l < r) {
                int currentNum = nums[i] + nums[l] + nums[r];
                if(Math.abs(closestNum - target) > Math.abs(currentNum - target)) {
                    closestNum = currentNum;
                }
                if(currentNum > target) {
                    r--;
                }
                if(currentNum < target) {
                    l++;
                }
                if(currentNum == target) {
                    return target;
                }
            }
        }
        return closestNum;
    }
    
    public static int getSubCloset(int[] nums, int begin, int end, int target) {
        if((end - begin) == 1) {
            return Math.min(nums[end]-target, target-nums[begin]);
        }
        
        int mid = (begin+end)/2;
        int mid1 = (begin+end+1)/2;
        
        if(target > nums[mid1]) {
            return getSubCloset(nums,mid1,end,target);
        }
        if(target < nums[mid]) {
            return getSubCloset(nums, begin, mid, target);
        }
        return Math.min(nums[mid1]-target, target-nums[mid]);
    }
}
