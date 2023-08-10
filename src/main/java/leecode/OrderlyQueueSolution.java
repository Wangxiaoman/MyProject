package leecode;

import java.util.Arrays;
import java.util.Collections;

public class OrderlyQueueSolution {
    /**
     * @param s: a string
     * @param k: int
     * @return: the lexicographically smallest string
     */
    public static String orderlyQueue(String s, int k) {
        // Write your code here.
        if(k == 1) {
            String min = new String(s);
            String current = new String(s);;
            for (int i = 0; i < s.length(); i++) {
                current = current.substring(1, current.length()) + current.charAt(0);
                if(min.compareTo(current) > 0) {
                    min = current;
                }
            }
            return min;
        } else {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            return new String(array);
        }
    }
    
    public static String stringShift(String s, int[][] shift) {
        // write your code here
        int left = 0;
        for(int i=0;i<shift.length;i++){
            if(shift[i][0] == 0){
                left += shift[i][1];
            } else {
                left -= shift[i][1];
            }
        }
        
        left = left % s.length();
        int ll = (left > 0) ? s.length() - left: Math.abs(left);
        return s.substring(s.length()-ll, s.length()) + s.substring(0,s.length()-ll);
    }
    
    public static void main(String[] args) {
//        System.out.println(orderlyQueue("cba", 1));
        int[][] ss = new int[][] {{1, 6}, {0, 9}, {0, 5}, {1, 2}, {1, 7}, {0, 3}, {0, 8}, {1, 4}, {1, 1}, {0, 10}};
        System.out.println(stringShift("tuvwxyzabcd",ss));
    }
}
