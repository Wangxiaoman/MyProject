package leecode;

public class AnsSolution {
    /**
     * @param v1: the speed of GGbond
     * @param v2: the speed of SuperQ
     * @param s: the speed that lollipop can add
     * @param w: the cost of lollipop
     * @return: the minimal price
     */
    public static int getAns(int v1, int v2, int[] s, int[] w) {
        if (v1 > v2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length; i++) {
            if (v1 + s[i] > v2 && w[i] < min) {
                min = w[i];
            }
        }
        return min;
    }
    
    public static void main(String[] args) {
        System.out.println(getAns(3,10,new int[]{2,6,4,8,6,3},new int[]{3,3,5,6,4,5}));
    }
}
