package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightingLongestTime {
    /**
     * @param operation: A list of operations.
     * @return: The lamp has the longest liighting time.
     */
    public static char longestLightingTime(List<List<Integer>> operation) {
        int maxLighting = 0;
        int result = operation.get(0).get(0);
        int lastLighting = operation.get(0).get(1);
        for (int i = 1; i < operation.size(); i++) {
            List<Integer> o = operation.get(i);
            int index = o.get(0);
            int second = o.get(1);
            int ls = second - lastLighting;
            if(ls > maxLighting) {
                maxLighting = ls;
                result = index;
            }
            lastLighting = second;
        }
        return (char) (result + 97);
    }
    
    public static void main(String[] args) {
        List<List<Integer>> operation = new ArrayList<>();
//        operation.add(Arrays.asList(6,5));
//        operation.add(Arrays.asList(17,12));
//        operation.add(Arrays.asList(24,14));
//        operation.add(Arrays.asList(10,26));
//        operation.add(Arrays.asList(16,30));
//        operation.add(Arrays.asList(3,36));
//        operation.add(Arrays.asList(14,72));
//        operation.add(Arrays.asList(18,82));
//        operation.add(Arrays.asList(15,87));
//        operation.add(Arrays.asList(2,97));
        operation.add(Arrays.asList(0,2));
        operation.add(Arrays.asList(1,5));
        operation.add(Arrays.asList(0,9));
        operation.add(Arrays.asList(2,15));
        char c = longestLightingTime(operation);
        System.out.println(c);
    }
}
