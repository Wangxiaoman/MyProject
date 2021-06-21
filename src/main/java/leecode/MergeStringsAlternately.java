package leecode;

// 1768. 交替合并字符串
public class MergeStringsAlternately {
    public static String mergeAlternately(String word1, String word2) {
        StringBuffer sb = new StringBuffer();
        int i=0;
        while(true) {
            if(i==word1.length() && word2.length() == word1.length()) {
                return sb.toString();
            }
            
            if(i==word1.length() && word2.length() > word1.length()) {
                sb.append(word2.substring(i, word2.length()));
                return sb.toString();
            }
            
            if(i==word2.length() && word1.length() > word2.length()) {
                sb.append(word1.substring(i, word1.length()));
                return sb.toString();
            }
            
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
            i++;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(mergeAlternately("ab", "pqrs"));
        System.out.println(mergeAlternately("efed", "rs"));
    }
}
