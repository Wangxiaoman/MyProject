package leecode;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * @author xiaoman
 * 
 * 说明：所有的数异或，那么相同的数字异或
 */
public class SingleNumber {
  public static int singleNumber(int[] nums) {
    int result = 0;
    for(int i : nums){
      result = result ^ i;
    }
    return result;
  }
  
  public static void main(String[] args) {
    System.out.println(singleNumber(new int[]{1,1,2,2,3,4,4}));
    System.out.println(1^1^3);
  }
}
