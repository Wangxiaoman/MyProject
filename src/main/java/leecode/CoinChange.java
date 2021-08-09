package leecode;

import java.util.Arrays;

// f(amount) = min(f(amonut - coin1),f(amount - coin2)....)+1
public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if(amount == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int coin : coins) {
            int counter = coinChange(coins, amount - coin);
            if (counter == -1) {
                continue;
            }
            if(res < 0 && counter >= 0) {
                res = counter + 1;
            } else {
                res = Math.min(counter + 1, res);
            }
        }
        return res == Integer.MIN_VALUE ? -1 : res;
    }

    public static int coinChange1(int[] coins, int amount) {
        if(amount <= 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] counter = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            int cost = Integer.MIN_VALUE;
            for (int index = 0; index < coins.length; index++) {
                int coin = coins[index];
                if (i - coin == 0) {
                    cost = 1;
                    break;
                } else {
                    if(i > coin) {
                        int newCost = counter[i - coin] + 1;
                        if(newCost > 0) {
                            if(cost > 0) {
                                cost = Math.min(cost, newCost);
                            } else {
                                cost = newCost;
                            }
                        }
                    }
                }
            }
            counter[i] = cost;
        }
        return counter[amount] == Integer.MIN_VALUE ? -1 : counter[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[] {5,7}, 13));
        System.out.println(coinChange(new int[] {1,2,5}, 13));
        System.out.println(coinChange(new int[] {1,2,5}, 1));
        System.out.println(coinChange(new int[] {1,2,5}, 12));
    }
}
