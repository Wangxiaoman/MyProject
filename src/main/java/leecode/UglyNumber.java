package leecode;

import java.util.ArrayList;
import java.util.List;

// 1. 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。

// 2. 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

public class UglyNumber {

    public static int getIndexUgly(int index) {
        int[] nums = new int[index];
        nums[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < index; i++) {
            int n2 = nums[p2] * 2;
            int n3 = nums[p3] * 3;
            int n5 = nums[p5] * 5;
            nums[i] = Math.min(n2, Math.min(n3, n5));
            if (n2 == nums[i])
                p2++;
            if (n3 == nums[i])
                p3++;
            if (n5 == nums[i])
                p5++;
        }
        return nums[index - 1];
    }

    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        if (n <= 5) {
            return true;
        }

        if (n % 2 == 0) {
            return isUgly(n / 2);
        }
        if (n % 3 == 0) {
            return isUgly(n / 3);
        }
        if (n % 5 == 0) {
            return isUgly(n / 5);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isUgly(1));
        System.out.println(isUgly(6));
        System.out.println(isUgly(8));
        System.out.println(isUgly(14));
        System.out.println(isUgly(121));

        System.out.println(getIndexUgly(493));
    }
}
