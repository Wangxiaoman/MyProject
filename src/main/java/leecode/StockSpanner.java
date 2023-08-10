package leecode;

import java.util.ArrayList;
import java.util.List;

public class StockSpanner {
    private List<Integer> prices;
    public StockSpanner() {
        prices = new ArrayList<>();
    }
    /**
     * @param price: 
     * @return: int
     */
    public int next(int price) {
        // Write your code here.
        prices.add(price);
        int counter = 0;
        for (int i = prices.size() - 1; i >= 0; i--) {
            if(price >= prices.get(i)) {
                counter ++;
            } else {
                return counter;
            }
        }
        return counter;
    }
    
    public static void main(String[] args) {
        StockSpanner ss = new StockSpanner();
        System.out.println(ss.next(50));
        System.out.println(ss.next(80));
        System.out.println(ss.next(80));
        System.out.println(ss.next(70));
        System.out.println(ss.next(90));
        System.out.println(ss.next(75));
        System.out.println(ss.next(85));
    }
}
