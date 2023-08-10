package leecode;

import java.util.ArrayList;
import java.util.List;

public class DiStringMatchSolution {
    /**
     * @param s:
     * @return: nothing 输入："IDID" 输出：[0,4,1,3,2] 输入："III" 输出：[0,1,2,3] 
     */
    public static int[] diStringMatch(String s) {
        List<Integer> dlist = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'D') {
                dlist.add(i);
            }
        }
        int[] result = new int[s.length() + 1];
        int max = s.length();
        if (!dlist.isEmpty()) {
            for (int i = 0; i < dlist.size(); i++) {
                result[dlist.get(i)] = max--;
            }
        }
        int min = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 0) {
                result[i] = min++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
         int[] result =  diStringMatch("IDIDIDI");
         //diStringMatch("III");
         for(int i: result) {
             System.out.print(i+",");
         }
         System.out.println();
         
    }
}
