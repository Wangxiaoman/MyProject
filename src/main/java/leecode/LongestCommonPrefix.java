/**
 * 
 */
package leecode;

import org.apache.commons.lang.StringUtils;

/**
 * @author linkedme
 *
 */
public class LongestCommonPrefix {
    
    public static String longestCommonPrefix(String[] strs) {
//        int minLength = strs[0].length();
//        for(int i=1;i<strs.length;i++){
//            if(strs[i].length() < minLength){
//                minLength = strs[i].length();
//            }
//        }
        
        
        StringBuffer sb = new StringBuffer();
        int index = 0;
        char tempChar = 0;
        if(strs.length == 0) {
            return StringUtils.EMPTY;
        }
        if(strs.length == 1) {
            return strs[0];
        }
        while(true){
            for(int i=0;i<strs.length-1;i++){
                if(strs[i].length() == index){
                    return sb.toString();
                }
                if(strs[i].length()<=index || strs[i+1].length() <=index) {
                    return sb.toString();
                }
                if(strs[i].charAt(index) != strs[i+1].charAt(index)){
                    return sb.toString();
                }
                tempChar = strs[i].charAt(index);
            }
            sb.append(String.valueOf(tempChar));
            index++;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {"ab", "a"}));
        System.out.println(longestCommonPrefix(new String[]{"abc","abcd"}));
        System.out.println(longestCommonPrefix(new String[]{""}));
        System.out.println(longestCommonPrefix(new String[]{"abc","abcd","abad","abbb"}));
    }
}
