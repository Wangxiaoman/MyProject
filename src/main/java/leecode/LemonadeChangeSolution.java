package leecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemonadeChangeSolution {
    /**
     * @param bills: the Bill
     * @return: Return true if and only if you can provide every customer with correct change.
     */
    public boolean lemonadeChange(List<Integer> bills) {
        // Write your code here.
        Map<Integer,Integer> billCount = new HashMap<>();
        for(int b : bills) {
            if(b == 5) {
                billCount.put(5, billCount.getOrDefault(5, 0) + 1);
            } else if(b == 10) {
                int count = billCount.getOrDefault(5, 0);
                if(count == 0) {
                    return false;
                } else {
                    billCount.put(5, count - 1);
                    billCount.put(10, billCount.getOrDefault(10, 0) + 1);
                }
            } else if(b == 20) {
                int count5 = billCount.getOrDefault(5, 0);
                int count10 = billCount.getOrDefault(10, 0);
                if(count5 == 0) {
                    return false;
                } else {
                    if(count10 > 0) {
                        billCount.put(5, count5 - 1);
                        billCount.put(10, count10 - 1);
                        billCount.put(20, billCount.getOrDefault(20, 0) + 1);
                    } else {
                        if(count5 < 3) {
                            return false;
                        } else {
                            billCount.put(5, count5 - 3);
                            billCount.put(20, billCount.getOrDefault(20, 0) + 1);
                        }
                    }
                }
            }
        }
        return true;
    }
}
