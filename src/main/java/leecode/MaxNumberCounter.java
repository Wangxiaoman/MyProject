package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MaxNumberCounter {
    
    public static int maximum(int[][] logs) {
        int[] counter = new int[3000];
        for(int[] log : logs) {
            for(int i=log[0]; i<log[1];i++) {
                ++counter[i];
            }
        }
        int max = 0;
        int index = 0;
        for(int i=0; i< counter.length;i++) {
            if(counter[i] > max) {
                max = counter[i];
                index = i;
            }
        }
        return index;
    }
    
    public static int maximumPopulation(int[][] logs) {
        Map<Integer, Integer> counter = new HashMap<>();
        for(int[] log : logs) {
            for(int i=log[0]; i<log[1];i++) {
                int count = counter.getOrDefault(i, 0);
                counter.put(i, count+1);
            }
        }
        
        int maxCount = 0;
        int maxYear = logs[0][0]; 
        for(Entry<Integer, Integer> entry : counter.entrySet()) {
            if(entry.getValue() > maxCount) {
                maxYear = entry.getKey();
                maxCount = entry.getValue();
            }else if(entry.getValue() == maxCount && maxYear > entry.getKey()) {
                maxYear = entry.getKey();
            }
        }
        return maxYear;
    }
    
    public static void main(String[] args) {
        System.out.println(maximumPopulation(new int[][] {{1993,1999},{2000,2010}}));
        System.out.println(maximumPopulation(new int[][] {{1950,1961},{1960,1971},{1970,1981}}));
        
        System.out.println(maximum(new int[][] {{1993,1999},{2000,2010}}));
        System.out.println(maximum(new int[][] {{1950,1961},{1960,1971},{1970,1981}}));
        
        System.out.println(maximum(new int[][] {{2000,2001}}));
    }
}
