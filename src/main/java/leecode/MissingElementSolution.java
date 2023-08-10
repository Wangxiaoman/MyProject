package leecode;

public class MissingElementSolution {
    /**
     * @param nums: An integer array
     * @param k: Find the kth missing number in nums
     * @return: nothing
     */
    public static int missingElement(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int sub = nums[i] - nums[i-1] - 1;
            k -= sub;
            if(k <= 0) {
                return nums[i] + k - 1;
            }
        }
        return nums[nums.length - 1] + k;
    }
    
    public static void main(String[] args) {
        System.out.println(missingElement(new int[] {1,3,5,7,9}, 1));
        System.out.println(missingElement(new int[] {1,3,5,7,9}, 4));
        System.out.println(missingElement(new int[] {2,3,4,5,7}, 4));
    }
    
}
