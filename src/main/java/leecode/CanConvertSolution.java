package leecode;

/**
 * @author xiaomanwang
 * 给两个字符串 S 和 T, 判断 S 能不能通过删除一些字母(包括0个)变成 T.
 */
public class CanConvertSolution {
    /**
     * @param s: string S
     * @param t: string T
     * @return: whether S can convert to T
     */
    public static boolean canConvert(String s, String t) {
        if(s ==null || s.isEmpty() || t.isEmpty() || t==null) {
            return true;
        }
        
        int sindex = 0;
        int tindex = 0;
        while(true) {
            if(t.charAt(tindex) == s.charAt(sindex)) {
                tindex++;
                sindex++;
            } else {
                sindex++;
            }
            if(tindex == t.length()) {
                return true;
            }
            if(sindex == s.length()) {
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(canConvert("adcadcadcdac","adddaa"));
        System.out.println(canConvert("adda","aad"));
        System.out.println(canConvert("lintcode","lint"));
        System.out.println(canConvert("lintcode","ide"));
    }
}
