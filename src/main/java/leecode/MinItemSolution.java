package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinItemSolution {
    public static int minItem(List<Integer> ids, int m) {
        Map<Integer,Integer> numCounter = new HashMap<>();
        ids.forEach(i -> numCounter.put(i, numCounter.getOrDefault(i, 0) + 1));
        List<Integer> valueList = new ArrayList<>(numCounter.values());
        Collections.sort(valueList);
        for(int index = 0; index < valueList.size(); index++) {
            m = m - valueList.get(index);
            if(m < 0) {
                return valueList.size() - index;
            } if(m == 0) {
                return valueList.size() - index - 1;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        
        System.out.println(minItem(Arrays.asList(1,1,1,2,2,3,3,4,4), 3));
        System.out.println(minItem(Arrays.asList(1,1,1,2,3,3), 3));
        System.out.println(minItem(Arrays.asList(1), 1));
    }
}
