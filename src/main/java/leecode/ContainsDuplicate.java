package leecode;

import java.util.Arrays;

/**
 * 
* @ClassName: ContainsDuplicate 
* @Description
* Given an array of integers, find if the array contains any duplicates. 
* Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
* @author xiaoman 
* @date 2015年12月8日 下午2:22:25 
*
 */
public class ContainsDuplicate {
	public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0 ;i<nums.length-1;i++){
        	if(nums[i] == nums[i+1]){
        		return true;
        	}
        }
        return false;
    }
	
	public static void main(String[] args) {
	}
}
