package leecode;
/**
 * @author xiaomanwang
 * [1,2,1,2,1,1] -> [1,1,1,1,2,2]
 */
public class SimpleSort {

    public static void sort(int[] nums) {
        int i=0;
        int j=nums.length-1;
        while(i<j) {
            while(nums[i] == 1 && i<j) {
                i++;
            }
            while(nums[j] == 2 && i<j) {
                j--;
            }
            if(i<j) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1,1,2,2,1};
        sort(nums);
        for(int n : nums) {
            System.out.print(n+",");
        }
    }
}
