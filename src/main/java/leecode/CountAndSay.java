package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 38. 外观数列
 * 
 * @author xiaomanwang
 */
public class CountAndSay {
    
    private static Map<Integer,String> intMap = new HashMap<>();
    public static String countAndSay(int n) {
        if(n == 1) {
            return "1";
        }
        intMap.put(1, "1");
        for (int i = 2; i <= n; i++) {
            String nstr = intMap.get(i-1);
            intMap.put(i, countAndSave(nstr));
        }
        return intMap.get(n);
    }
    
    public static String countAndSave(String nstr) {
        if(nstr.equals("1")) {
            return "11";
        }
        
        int counter = 1;
        char c = nstr.charAt(0);
        int strlength = nstr.length();
        StringBuffer result = new StringBuffer();
        for (int i = 1; i < strlength; i++) {
            if (c == nstr.charAt(i)) {
                counter++;
            } else {
                result.append(counter).append(c);
                counter = 1;
                c = nstr.charAt(i);
            }

            if (i == nstr.length() - 1) {
                result.append(counter).append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
        System.out.println(intMap);
    }
}
