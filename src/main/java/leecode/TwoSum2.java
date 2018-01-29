/**
 * 
 */
package leecode;

/**
 * @author linkedme
 * 
 * sorted in ascending order
 * Input: numbers={2, 7, 11, 15}, target=9  Output: index1=1, index2=2
 *
 */
public class TwoSum2 {
    
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int right = numbers.length - 1;
        int left = 0;
        boolean indexr = true; 
        while(left < right){
            if(target < numbers[right]){
                right--;
                continue;
            }
            if(indexr){
                int leftTemp = target - numbers[right];
                if(leftTemp == numbers[left]){
                    result[0] = left+1;
                    result[1] = right+1;
                    return result;
                }
                if(leftTemp > numbers[left]){
                    left ++;
                    indexr = false;
                    continue;
                }
            }else{
                int rightTemp = target - numbers[left];
                if(rightTemp == numbers[right]){
                    result[0] = left+1;
                    result[1] = right+1;
                    return result;
                }
                if(rightTemp < numbers[right]){
                    right --;
                    indexr = true;
                    continue;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] result = twoSum(new int[]{-1,0},-1);
        System.out.println(result[0]+","+result[1]);
    }
}
