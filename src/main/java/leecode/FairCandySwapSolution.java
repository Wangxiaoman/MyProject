package leecode;

import java.util.Arrays;

public class FairCandySwapSolution {
    /**
     * @param a: an array
     * @param b: an array
     * @return: an integer array
     */
    public int[] fairCandySwap(int[] a, int[] b) {
        // Write your code here.
        Arrays.sort(a);
        Arrays.sort(b);
        
        int suma = Arrays.stream(a).sum();
        int sumb = Arrays.stream(b).sum();
        
        int sub = sumb - suma;
        
        int[] result = new int[2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if((b[j] - a[i])*2 == sub) {
                    result[0] = a[i];
                    result[1] = b[j];
                    return result;
                }
            }
        }
        return result;
    }
}
