package leecode;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is
 * equal to the product of all the elements of nums except nums[i]. Solve it without division and
 * inO(n). For example, given [1,2,3,4], return [24,12,8,6]. Follow up: Could you solve it with
 * constant space complexity? (Note: The output array does not count as extra space for the purpose
 * of space complexity analysis.) Subscribe to see which companies asked this question
 * @author xiaoman 
 *  由于output[i] = (x0 * x1 * ... * xi-1) * (xi+1 * .... * xn-1) 因此执行两趟循环：
 *  第一趟正向遍历数组，计算x0 ~ xi-1的乘积 
 *  第二趟反向遍历数组，计算xi+1 ~ xn-1的乘积
 */
public class ProductArray {
  public static int[] productExceptSelf(int[] nums) {
    int[] result = new int[nums.length];
    int l = 1;
    result[0] = 1;
    for (int j = 0; j < result.length-1; j++) {
      l *= nums[j];
      result[j+1] = l;
    }
    int r = 1;
    for(int i=nums.length-1;i>=1;i--){
      r *= nums[i];
      result[i-1] *= r;
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = productExceptSelf(new int[] { 1, 2, 3, 4, 5 });
    for(int num: nums){
      System.out.println(num);
    }
  }
}
