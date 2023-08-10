package leecode;

import java.util.ArrayList;
import java.util.List;

public class LargeGroupPositionsSolution {
    /**
     * @param s: a string
     * @return: the starting and ending positions of every large group
     */
    public static List<List<Integer>> largeGroupPositions(String s) {
        s = s + "1";
        List<List<Integer>> result = new ArrayList<>();
        int ccount = 1;
        int bindex = 0;
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char cc = s.charAt(i);
            if (cc == c) {
                ccount++;
            }
            if (cc != c) {
                if (ccount >= 3) {
                    List<Integer> t = new ArrayList<>();
                    t.add(bindex);
                    t.add(i - 1);
                    result.add(t);
                }
                bindex = i;
                c = cc;
                ccount = 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(largeGroupPositions("aaabbcccc"));
        System.out.println(largeGroupPositions("abcdddeeeeaabbbcd"));
        System.out.println(largeGroupPositions("wwwwwwwwwwg"));
        System.out.println(largeGroupPositions("aaa"));
    }
}
