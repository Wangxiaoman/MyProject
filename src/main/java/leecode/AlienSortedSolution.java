package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaomanwang
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。 
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，
 * 只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false
 */
public class AlienSortedSolution {
    /**
     * @param words: the array of string means the list of words
     * @param order: a string indicate the order of letters
     * @return: return true or false
     */
    public static boolean isAlienSorted(String[] words, String order) {
        // write your code here.
        Map<Character,Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            orderMap.put(c, i);
        }
        if(words.length == 1) {
            return true;
        }
        
        for (int i = 1; i < words.length; i++) {
            if(compare(words[i],words[i-1], orderMap) < 0) {
                return false;
            }
        }
        return true;
    }
    
    private static int compare(String str1, String str2, Map<Character, Integer> orderMap) {
        if (str1.equals(str2)) {
            return 0;
        } else {
            boolean s1max = str1.length() > str2.length();
            for (int i = 0; i < (s1max ? str2.length() : str1.length()); i++) {
                int n = orderMap.get(str1.charAt(i)) - orderMap.get(str2.charAt(i));
                if (n > 0)
                    return 1;
                if (n < 0)
                    return -1;
            }
            return s1max ? 1 : -1;
        }
    }
    
    public static void main(String[] args) {
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(isAlienSorted(new String[] {"hello","leetcode","kabc"}, order));
    }
}
