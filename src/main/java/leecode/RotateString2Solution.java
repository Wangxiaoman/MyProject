package leecode;

public class RotateString2Solution {
    /**
     * @param str: An array of char
     * @param left: a left offset
     * @param right: a right offset
     * @return: return a rotate string
     */
    public static String rotateString2(String str, int left, int right) {
        int leftMove = (left - right) % str.length();
        if (leftMove == 0) {
            return str;
        } else if (leftMove > 0) {
            return str.substring(leftMove, str.length()) + str.substring(0, leftMove);
        } else {
            return str.substring(str.length() + leftMove, str.length())
                    + str.substring(0, str.length() + leftMove);
        }
    }

    public static void main(String[] args) {
        System.out.println(rotateString2("abcdefg", 3, 1));
        System.out.println(rotateString2("abcdefg", 1, 2));
        System.out.println(rotateString2("abcdefg", 1, 1));
    }
}
