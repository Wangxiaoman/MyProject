package qlm;

public class QLMGoods {
    static int good[] = { 509, 838, 924, 650, 604, 793, 564, 651, 697, 649, 747, 787, 701, 605, 644 };
    static int max = 5000;
    
    static int[][] mark = new int[max][good.length];
    
    // i 第i件货
    // remain 剩余空间
    // 返回 最多能装下多少货物
    private static int make(int i,int remain){
        // 只有一件货，那么选择只有装或者不装
        if(i==0){
            return remain>good[i]?good[i]:0;
        }
        
        // 装货
        int planA = 0;
        if(remain > good[i]){
            planA = make(i-1,remain-good[i]) + good[i];
        }
        
        // 不装货
        int planB = make(i-1,remain);
        
        return planA>planB?planA:planB;
    }
    
    public static void main(String[] args) {
        int result = make(good.length - 1, max);
        System.out.println(result);
    }
}
