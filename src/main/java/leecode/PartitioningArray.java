package leecode;

import java.util.HashMap;
import java.util.Map;

public class PartitioningArray {
    /**
     * @param a: Integer array
     * @param k: a integer
     * @return: return is possible to partition the array satisfying the above conditions
     */
    public boolean partitioningArray(int[] a, int k) {
        // write your code here
        if(a.length == 1) {
            return true;
        }
        if(a.length == 0 || a.length % k != 0) {
            return false;
        }
        
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : a) {
            int count = map.getOrDefault(i, 0);
            if(count >= k) {
                return false;
            } else {
                map.put(i, count+1);
            }
        }
        return true;
    }
}
