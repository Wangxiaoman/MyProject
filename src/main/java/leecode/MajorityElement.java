package leecode;

import java.util.Arrays;

/**
 * 
* @ClassName: MajorityElement 
* @Description: 
*   Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
*   You may assume that the array is non-empty and the majority element always exist in the array.
* @author xiaoman 
* @date 2015年12月19日 下午5:36:50 
*
 */
public class MajorityElement {
  public static int majorityElement(int[] nums) {
    if(nums.length == 1){
      return nums[0];
    }
    Arrays.sort(nums);
    int count = 1;
    for(int i=0;i<nums.length-1;i++){
      if(nums[i] == nums[i+1]){
        count ++ ;
      }else{
        count = 1;
      }
      if(count > nums.length/2){
        return nums[i];
      }
    }
    return 0;
  }
  
  public static void main(String[] args) {
    int[] nums = {1,2,4,5,5,5,5};
    System.out.println(majorityElement(nums));
  }
}
