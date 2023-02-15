package leecode;

import java.util.HashMap;
import java.util.Map;

public class MaxCountNumber {
    public static int findNumber(int[] array) {
        Map<Integer,Integer> numberCounter = new HashMap<>();
        int num = -1;
        int count = 0;
        for(Integer a : array) {
            int aCount = numberCounter.getOrDefault(a, 0) + 1; 
            numberCounter.put(a, aCount);
            if(aCount > count || (aCount == count && num > a)) {
                num = a;
                count = aCount;
            }
        }
        return num;
    }
    
    public static void main(String[] args) {
        
    }
}
