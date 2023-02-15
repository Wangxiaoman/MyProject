package leecode;

import java.util.*;

public class LambdaSort {
    public static void sortByLength(String[] strArr) {
        Arrays.asList(strArr).sort((s1,s2) -> {
            return s1.length() -s2.length();
        });
    }
    
    public static void main(String[] args) {
//        String[] tt = new String[] {"1","12","123","a"};
        String[] tt = new String[] {"he","see","a","ball"};
        sortByLength(tt);
        for (String t : tt) {
            System.out.print(t+" ");
        }
    }

}
