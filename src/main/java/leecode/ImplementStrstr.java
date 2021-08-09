package leecode;

public class ImplementStrstr {
    
    public static int strStr(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() == 0) {
            return 0;
        }
        if(haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }
        
        int hlength = haystack.length();
        int nlength = needle.length();
        
        if(hlength < nlength) {
            return -1;
        }
        
        int matchIndexBegin = -1;
        int matchIndex = 0;
        for (int i=0; i < hlength; i++) {
            if(matchIndex >= nlength) {
                return matchIndexBegin;
            }
            if(haystack.charAt(i) == needle.charAt(matchIndex)) {
                if(matchIndexBegin < 0) {
                    matchIndexBegin = i;
                }
                matchIndex++;
            } else {
                matchIndex = 0;
                if(matchIndexBegin != -1) {
                    i = matchIndexBegin;
                }
                matchIndexBegin = -1;
            }
        }
        if(matchIndex < nlength) {
            return -1;
        }
        return matchIndexBegin;
    }
    
    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issipi"));
        System.out.println(strStr("","a"));
        System.out.println(strStr("a",""));
        System.out.println(strStr("a","a"));
        System.out.println(strStr("hello","ll"));
        System.out.println(strStr("mississippi","issip"));
        System.out.println(strStr("aabaaabaaac","aabaaac"));
    }
}
