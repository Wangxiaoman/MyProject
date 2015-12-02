package leecode;
/**
 * 
* @ClassName: MoveZeroes 
* @Description: 
*  Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
*  For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
* @author xiaoman 
* @date 2015年12月2日 下午2:22:15 
*
 */
public class MoveZeroes {
	public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
        	return;
        }
        
        for(int i=0;i<nums.length;i++){
        	for(int j=i;j<nums.length;j++){
        		if(nums[i] == 0){
        			int temp = nums[i];
                	nums[i] = nums[j];
                	nums[j] = temp;
        		}
        		
        	}
        }
    }
	
	public static void main(String[] args) {
		int[] nums = {1,2,0,3,0,1,2,3,4,0,21};
		moveZeroes(nums);
		
		for(int i : nums){
			System.out.print(i+",");
		}
	}
}
