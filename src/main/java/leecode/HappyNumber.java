/**
 * 
 */
package leecode;

import java.util.HashSet;

/**
 * @author linkedme
 * 202 题
 *
 */
public class HappyNumber {
    private static HashSet<Integer> set = new HashSet<Integer>();
    public static boolean isHappy(int n) {
        if(set.contains(n)){
            return false;
        }
        set.add(n);
        int result = 0;
        while(n > 0){
            int temp = n % 10;
            result += temp*temp;
            n = n/10;
        }
        
        if(result == 1){
            return true;
        }
        return isHappy(result);
    }
    
    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(20));
        System.out.println(isHappy(1111111));
    }
}
