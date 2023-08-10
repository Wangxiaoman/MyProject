package leecode;

import java.util.HashSet;
import java.util.Set;

public class ToGoatLatinSolution {
    /**
     * @param s: 
     * @return: nothing
     */
    public static String toGoatLatin(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        String[] ss = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ss.length; i++) {
            char c = ss[i].toLowerCase().charAt(0);
            if(set.contains(c)) {
                sb.append(ss[i]).append("ma");
            } else {
                if(ss[i].length() > 1) {
                    sb.append(ss[i].substring(1)).append(ss[i].charAt(0));
                } else {
                    sb.append(ss[i]);
                }
                sb.append("ma");
            }
            for(int j=0;j<=i;j++) {
                sb.append("a");
            }
            sb.append(" ");
        }
        return sb.deleteCharAt(sb.lastIndexOf(" ")).toString();
    }
    
    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
    }
}
