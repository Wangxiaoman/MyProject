package leecode;

public class LuckyNumberSolution {
    /**
     * @param n: count lucky numbers from 1 ~ n
     * @return: the numbers of lucky number
     */
    public static int luckyNumber(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            int tmp = i;
            while (tmp > 0) {
                if (tmp%10 == 8) {
                    count++;
                    break;
                } else {
                    tmp = tmp / 10;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(luckyNumber(100));
    }
}
