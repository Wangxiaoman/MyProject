package leecode;

import java.util.ArrayList;
import java.util.List;

public class CalculateNumberSolution {
    /**
     * @param num: the num
     * @return: the array subject to the description
     */
    public int[] calculateNumber(int num) {
        // Write your code here.
        String bs = Integer.toBinaryString(num);
        List<Integer> list = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < bs.length(); i++) {
            int n = bs.charAt(i) - 48;
            if(n == 1) {
                counter ++;
                list.add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        result.add(counter);
        result.addAll(list);
        return result.stream().mapToInt(i->i).toArray();
    }
    
    public static void main(String[] args) {
        
    }
}
