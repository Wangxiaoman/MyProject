package leecode;

/**
 * @author xiaomanwang
 * 我们对有趣三元组的定义如下：
    在一个整数数组 nums 中，存在三个不同的下标 i、j、k，并且三个下标的关系为 0≤i<j<k<nums.length
    对于以上三个递增的下标，有 nums[i]<nums[j]<nums[k] 满足以上条件，则可以说这个整数数组中存在有趣三元组。
 */
public class FunTupleSolution {
    /**
     * @param nums: 
     * @return: Returns the existence of an interesting triplet
     */
    public static boolean getFunTuple(int[] nums) {
        // write your code here
        int[] beyondCount = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            int count = 0;
            int temp = 0;
            for (int j = 0; j < i; j++) {
                int newTemp = nums[i] - nums[j];
                if(newTemp > 0 && newTemp != temp) {
                    temp = newTemp;
                    count = count+1;
                }
            }
            beyondCount[i] = count;
        }
        
        for(int c : beyondCount) {
            if(c >= 2) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(getFunTuple(new int[] {1,2,3,4,5}));
        System.out.println(getFunTuple(new int[] {1,2,3}));
        System.out.println(getFunTuple(new int[] {1,2,2,3,3}));
        System.out.println(getFunTuple(new int[] {1,2,2,2,1}));
        System.out.println(getFunTuple(new int[] {1,1,1,1,2}));
    }
}
