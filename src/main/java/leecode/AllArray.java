package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用递归做全排列
 * 
 * @author xiaoman
 *
 */
public class AllArray {

    static String[] base = {"a", "b", "c", "d"};

    static List<String> getArray(int i) {
        List<String> result = new ArrayList<>();
        String basei = base[i];
        if (i == 0) {
            result.add(basei);
            return result;
        }
        List<String> lastResult = getArray(i - 1);
        for (String temp : lastResult) {
            for (int j = 0; j <= temp.length(); j++) {
                StringBuffer sb = new StringBuffer();
                if (j == 0) {
                    sb.append(basei).append(temp);
                } else if (j == temp.length()) {
                    sb.append(temp).append(basei);
                } else {

                    sb.append(temp.substring(0, j)).append(basei).append(temp.substring(j));
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> result = getArray(base.length - 1);
        for (String str : result) {
            System.out.println(str);
        }
    }
}
