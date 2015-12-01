package leecode;
/**
 * 
* @ClassName: RemoveElement 
* @Description: Given an array and a value, remove all instances of that value in place and return the new length.
*               The order of elements can be changed. It doesn't matter what you leave beyond the new length.
* @author xiaoman 
* @date 2015年11月30日 下午1:42:47 
*
 */
public class RemoveElement {
	public static int removeElement(int[] nums, int val) {
        int j = 0;
        for(int i=0;i<nums.length;i++){
        	if(nums[i] == val){
        		continue;
        	}
        	nums[j] = nums[i];
        	j++;
        }
        
        return j;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,2,3};
		
		System.out.println(removeElement(nums,2));
		
		for(int num : nums){
			System.out.print(num+",");
		}
		
		
	}
}
