package leecode;

public class FormMinimumNumberSolution {
    /**
     * @param str: the pattern
     * @return: the minimum number
     */
    public static String formMinimumNumber(String str) {
        // Write your code here.
        int[] arrays = new int[10];
        arrays[0] = 1;
        int currentMaxNum = 1;
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c == 'I') {
                currentMaxNum = currentMaxNum + 1;
                arrays[i + 1] = currentMaxNum;
            }
            if (c == 'D') {
                for (int j = 0; j <= i; j++) {
                    arrays[j] = arrays[j] + 1;
                }
                arrays[i+1] = 1;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arrays.length; i++) {
            if(arrays[i] > 0) {
                sb.append(arrays[i]);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(formMinimumNumber("II"));
        System.out.println(formMinimumNumber("DIDI"));
        System.out.println(formMinimumNumber("DDIDDIID"));
    }
}
