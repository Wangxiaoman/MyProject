/**
 * 
 */
package leecode;

/**
 * @author linkedme
 *
 */
public class ReverseString {
    public static String reverseString(String s) {
        char[] result = new char[s.length()];
        for(int i=0;i<s.length();i++){
            result[s.length()-i-1] = s.charAt(i);
        }
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(reverseString("abdsds"));
    }
}
