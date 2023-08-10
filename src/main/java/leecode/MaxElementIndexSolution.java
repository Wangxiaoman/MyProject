package leecode;

import java.util.List;

public class MaxElementIndexSolution {
    /**
     * @param arr: array
     * @param helper: You can using compare function by `helper.compare(int, int, int, int)`
     * @return: max element index
     */
    public int getMaxElementIndex(List<Integer> arr, Helper helper) {
        int b = 0;
        int e = arr.size() - 1;
        while (b != e) {
            int m1 = (b + e) / 2;
            int m2 = (b + e + 1) / 2;
            int c = helper.compare(b, m1, m2, e);
            if (c == 1) {
                e = m1;
            } else {
                b = m2;
            }
        }
        return b;
        
    }
    private static class Helper{
        public int compare(int a,int b,int c, int d) {
            return 1;
        }
    }
}
