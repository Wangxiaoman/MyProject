/**
 * 
 */
package leecode;

/**
 * @author linkedme
 * 461
 *
 */
public class HammingDistance {
    public static int hammingDistance(int x, int y) {
        int temp = x^y;
        int result = 0;
        while(temp > 0){
            if(temp % 2 == 1){
                result ++;
            }
            temp = temp / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));
        System.out.println(hammingDistance(1,3));
    }
}
