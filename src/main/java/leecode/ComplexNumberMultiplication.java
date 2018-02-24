/**
 * 
 */
package leecode;

/**
 * @author linkedme
 * 
 *         537
 *
 */
public class ComplexNumberMultiplication {
    public static String complexNumberMultiply(String a, String b) {
        String[] ans = a.split("\\+");
        String[] bns = b.split("\\+");

        int an1 = Integer.valueOf(ans[0]);
        int an2 = Integer.valueOf(ans[1].replaceAll("i", ""));

        int bn1 = Integer.valueOf(bns[0]);
        int bn2 = Integer.valueOf(bns[1].replaceAll("i", ""));

        int cn1 = an1 * bn1 - an2 * bn2;
        int cn2 = an1 * bn2 + an2 * bn1;

        return String.valueOf(cn1 + "+" + cn2 + "i");
    }

    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
    }
}
