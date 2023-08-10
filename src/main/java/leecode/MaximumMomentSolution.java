package leecode;

/**
 * @author xiaomanwang
 * 给一个24小时制的时间（00:00-23:59)，其中有一个或多个数字是问号。
 * 问号处可以用任何一个数字代替，问可以表示的最大时间是多少。
 */
public class MaximumMomentSolution {
    /**
     * @param time: a string of Time
     * @return: The MaximumMoment
     */
    public static String maximumMoment(String time) {
        // Write your code here.
        char c1 = time.charAt(0);
        char c2 = time.charAt(1);
        char c3 = time.charAt(3);
        char c4 = time.charAt(4);

        Character c1Max = null;
        if (c1 == '?') {
            if(c2 != '?' && c2 >= '4') {
                c1Max = '1';
            } else {
                c1Max = '2';
            }
        } else {
            c1Max = c1;
        }

        Character c2Max = null;
        if (c2 == '?') {
            if (c1Max == '2') {
                c2Max = '3';
            } else {
                c2Max = '9';
            }
        } else {
            c2Max = c2;
        }

        Character c3Max = null;
        if (c3 == '?') {
            c3Max = '5';
        } else {
            c3Max = c3;
        }

        Character c4Max = null;
        if (c4 == '?') {
            c4Max = '9';
        } else {
            c4Max = c4;
        }

        return c1Max +""+ c2Max + ":" + c3Max +""+ c4Max;
    }
    
    public static void main(String[] args) {
        System.out.println(maximumMoment("??:??"));
        System.out.println(maximumMoment("1?:??"));
        System.out.println(maximumMoment("1?:3?"));
    }
}
