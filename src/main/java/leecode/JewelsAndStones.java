/**
 * 
 */
package leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author linkedme
 *
 */
public class JewelsAndStones {
    public static int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for(int i=0;i<J.length();i++){
            set.add(J.charAt(i));
        }
        
        int result=0;
        for(int i=0;i<S.length();i++){
            if(set.contains(S.charAt(i))){
                result ++;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA","aAAbbbb"));
        System.out.println(numJewelsInStones("z","ZZZ"));
    }
}
