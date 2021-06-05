package leecode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

// 剑指 Offer 50. 第一个只出现一次的字符
// 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
public class FirstChar {
    
    public static void main(String[] args) {
        System.out.println(firstUniqChar("aadadaad"));
    }
    public static char firstUniqChar(String s) {
        Map<Character, Integer> lm = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            lm.put(c, lm.getOrDefault(c, 0) + 1);
        }
        if (lm.size() == 0) {
            return ' ';
        } else {
            for(Entry<Character, Integer> entry : lm.entrySet()) {
                if(entry.getValue() == 1) {
                    return entry.getKey();
                }
            }
            return ' ';
        }
    }
}
