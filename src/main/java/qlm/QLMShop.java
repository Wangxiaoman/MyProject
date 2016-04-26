package qlm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class QLMShop {

  private static final String fileUrl = "/Users/xiaoman/Downloads/144043063958496.txt";
  private static Map<Integer, Integer> shopMap = new LinkedHashMap<>();
  private static BufferedReader br;

  private static void getResult() {
    try {
      long result = 0;
      br = new BufferedReader(new FileReader(new File(fileUrl)));
      String content = "";
      do {
        content = br.readLine();
        if (StringUtils.isNotBlank(content)) {
          String[] list = content.split(" ");
          if (list != null) {
            String operate = list[0];
            int number1 = Integer.valueOf(list[1]);
            int number2 = Integer.valueOf(list[2]);
            if (operate.equals("up")) {
              up(number1, number2);
            } else if (operate.equals("down")) {
              down(number1, number2);
            } else {
              result = result + query(number1, number2);
            }
          }
        }
      } while (StringUtils.isNoneBlank(content));

      System.out.println("result:" + result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int query(int beginPrice, int endPrice) {
    int sumCount = 0;
    for (int i = beginPrice; i <= endPrice; i++) {
      Integer count = shopMap.get(i);
      if (count != null) {
        sumCount = sumCount + count;
      }
    }
    return sumCount;
  }

  private static void up(int number, int price) {
    Integer count = shopMap.get(price);
    if (count == null) {
      shopMap.put(price, number);
    } else {
      shopMap.put(price, number + count);
    }
  }

  private static void down(int number, int price) {
    Integer count = shopMap.get(price);
    if (count != null) {
      shopMap.put(price, count - number);
    }
  }

  public static void main(String[] args) {
    getResult();
  }
}
