package leecode;
// 135. 分发糖果
/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
    你需要按照以下要求，帮助老师给这些孩子分发糖果：
    每个孩子至少分配到 1 个糖果。
    评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
    那么这样下来，老师至少需要准备多少颗糖果呢？
 * 
 * @author xiaomanwang
 *
 */
public class Candy {
    
    public static int candy(int[] ratings) {
        int[] candys = new int[ratings.length];
        // init
        for(int i=0;i<candys.length;i++) {
            candys[i] = 1;
        }
        // left
        for(int i=1;i<candys.length;i++) {
            if(ratings[i] > ratings[i-1] && candys[i] <= candys[i-1]) {
                candys[i] = candys[i-1] + 1;
            }
        }
        // right
        for(int i=1;i<candys.length;i++) {
            if(ratings[candys.length-i-1] > ratings[candys.length-i] && candys[candys.length-i-1] <= candys[candys.length-i]) {
                candys[candys.length-i-1] = candys[candys.length-i] + 1;
            }
        }
        
        int counter = 0;
        for(int i : candys) {
            counter = counter+i;
        }
        return counter;
    }
    
    public static void main(String[] args) {
        System.out.println(candy(new int[] {1,0,2}));
        System.out.println(candy(new int[] {1,2,2}));
        System.out.println(candy(new int[] {1,2,87,87,87,2,1}));
        System.out.println(candy(new int[] {1,6,10,8,7,3,2}));
    }
}
