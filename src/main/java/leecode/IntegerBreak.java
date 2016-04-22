package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize
 * the product of those integers. Return the maximum product you can get. For example, given n = 2,
 * return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * @author xiaoman
 * 
 * 分析：通过分析之后发现，这种数字，最后组合是3，2组成，3的数量尽可能的多，那么乘积的数量会最大，那么我们就根据这个原理写出代码就ok了
 */
public class IntegerBreak {
  public static int getMaxProduct(int num) {
    if (num == 2) {
      return 1;
    }
    if (num == 3) {
      return 2;
    }
    if (num > 3) {
      List<Integer> subList = new ArrayList<Integer>();
      do {
        num = num - 3;
        if (num == 0) {
          subList.add(3);
        } else if (num == 1) {
          subList.add(4);
          num = 0;
        } else if (num == 2) {
          subList.add(3);
          subList.add(2);
          num = 0;
        } else {
          subList.add(3);
          continue;
        }
      } while (num > 0);

      int result = 1;
      for (int i : subList) {
        result = result * i;
      }
      return result;
    }
    return 1;
  }

  public static void main(String[] args) {
    System.out.println(getMaxProduct(4));
    System.out.println(getMaxProduct(5));
    System.out.println(getMaxProduct(6));
    System.out.println(getMaxProduct(10));
    System.out.println(getMaxProduct(20));
  }
}
