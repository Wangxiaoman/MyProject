package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HighFiveSolution {
    /**
     * @param items: results a list of [student_id, score]
     * @return: find the average of 5 highest scores for each student
     */
    public int[][] highFive(int[][] items) {
        // Write your code here
        Map<Integer,List<Integer>> scoreMap = new TreeMap<>();
        for (int i = 0; i < items.length; i++) {
            int id = items[i][0];
            int score = items[i][1];
            
            List<Integer> scores = scoreMap.getOrDefault(id, new ArrayList<>());
            scores.add(score);
            scoreMap.put(id, scores);
        }
        
        int[][] result = new int[scoreMap.size()][2];
        int ri = 0;
        for(Entry<Integer, List<Integer>> entry : scoreMap.entrySet()) {
            int id = entry.getKey();
            List<Integer> scores = entry.getValue();
            Collections.sort(scores);
            if(scores.size() > 5) {
                scores = scores.subList(scores.size()-5, scores.size());
            }
            
            int sum = scores.stream().mapToInt(val -> val).sum();
            result[ri][0] = id;
            result[ri][1] = sum/5;
            ri++;
        }
        return result;
    }
}
