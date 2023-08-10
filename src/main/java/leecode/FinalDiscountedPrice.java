package leecode;
/**
 * @author xiaomanwang
 * 一位店主需要完成一项销售任务，他将要出售的物品排成一排。
 * 从左侧开始，店主以其全价减去位于该物品右侧的第一个价格较低或价格相同的商品的价格。
 * 如果右侧没有价格低于或等于当前商品价格的商品，则以全价出售当前商品。
 * 你需要返回每一个物品实际售出价格。
 */
public class FinalDiscountedPrice {
    /**
     * @param prices: a list of integer
     * @return: return the actual prices
     */
    public int[] finalDiscountedPrice(int[] prices) {
        // write your code here
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            boolean originValue = true;
            for (int j = i+1; j < prices.length; j++) {
                if(prices[i] >= prices[j]) {
                    result[i] = prices[i]-prices[j];
                    originValue = false;
                    break;
                }
            }
            if(originValue) {
                result[i] = prices[i];
            }
        }
        return result;
    }
}
