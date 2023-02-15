package leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 389. 找不同
 * @author xiaomanwang
 */
public class FindTheDifference {
    
    public static char findTheDifference(String s, String t) {
        if(s.equals("")) {
            return t.charAt(0);
        }
        Map<Character,Integer> smap = getCharMap(s);
        Map<Character,Integer> tmap = getCharMap(t);
        for(Entry<Character, Integer> entry : tmap.entrySet()) {
            int scount = smap.getOrDefault(entry.getKey(),0);
            if(entry.getValue() > scount) {
                return entry.getKey();
            }
        }
        return '1';
    }
    
    private static Map<Character,Integer> getCharMap(String str){
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        return map;
    }
    
    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd","abcde"));
    }
}
