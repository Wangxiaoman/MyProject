package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomanwang
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 */
public class IsMonotonicSolution {
    /**
     * @param a: a array
     * @return: is it monotonous
     */
    public static boolean isMonotonic(int[] a) {
        // Write your code here.
        if (a.length <= 2) {
            return true;
        }
        List<Integer> subList = new ArrayList<>();
        for (int i = 1; i < a.length; i++) {
            int sub = a[i] - a[i-1];
            if(sub != 0) {
                subList.add(sub);
            }
        }
        if(subList.isEmpty() || subList.size()==1) {
            return true;
        }
        
        for(int i=1;i<subList.size();i++) {
            if(!(subList.get(0)>0) == (subList.get(i)>0)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(isMonotonic(new int[] {6,5,4,4}));
    }
    
}
