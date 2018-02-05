/**
 * 
 */
package leecode;

/**
 * @author linkedme 561
 *
 */
public class ArrayPartitionI {
    public static int arrayPairSum(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i] < nums[nums.length - i - 1] ? nums[i] : nums[nums.length - i - 1];
            result = result + temp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[]{1,4,3,2}));
        System.out.println(arrayPairSum(new int[]{1,1,2,2}));
    }
}
