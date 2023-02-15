package leecode;

// 两数之和 II - 输入有序数组
public class TwoSumInputArrayIsSorted {
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while(true) {
            if(numbers[left] + numbers[right] == target) {
                return new int[] {left + 1, right + 1};
            }
            if(numbers[left] + numbers[right] < target) {
                left ++;
            }
            if(numbers[left] + numbers[right] > target) {
                right --;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] result = twoSum(new int[] {2,3,4},6);
        
        System.out.println(result[0]+":"+result[1]);
    }
}
