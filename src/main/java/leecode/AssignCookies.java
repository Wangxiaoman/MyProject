package leecode;

import java.util.Arrays;

// 455. 分发饼干
public class AssignCookies {
    
    public static int findContentChildren(int[] g, int[] s) {
        int gsize = g.length;
        int ssize = s.length;
        int counter = 0;
        
        Arrays.sort(g);
        Arrays.sort(s);
        
        while(gsize > 0 && ssize > 0) {
            if(s[ssize-1] >= g[gsize -1]) {
                counter++;
                ssize --;
                gsize --;
            } else {
                gsize --;
            }
        }
        return counter;
    }
    
    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[] {1,3,2},new int[] {1,1}));
        System.out.println(findContentChildren(new int[] {1,2},new int[] {1,3,2}));
        System.out.println(findContentChildren(new int[] {1,2,3},new int[] {1,2,3}));
    }
}
