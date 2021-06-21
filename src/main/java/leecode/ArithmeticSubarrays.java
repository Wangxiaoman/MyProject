package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1630. 等差子数组
public class ArithmeticSubarrays {

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int ln = l[i];
            int rn = r[i];

            List<Integer> temp = new ArrayList<>();
            for (int j = ln; j <= rn; j++) {
                temp.add(nums[j]);
            }

            Collections.sort(temp);
            if (temp.size() <= 1) {
                result.add(true);
                continue;
            }
            int step = temp.get(1) - temp.get(0);
            boolean tempResult = true;
            for (int k = 2; k < temp.size(); k++) {
                if (temp.get(k) - temp.get(k - 1) != step) {
                    tempResult = false;
                    break;
                }
            }
            result.add(tempResult);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkArithmeticSubarrays(new int[] {4, 6, 5, 9, 3, 7},
                new int[] {0, 0, 2}, new int[] {2, 3, 5}));
    }
}
