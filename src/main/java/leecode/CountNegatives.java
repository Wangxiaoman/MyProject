package leecode;

/**
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 
 * 请你统计并返回 grid 中 负数 的数目。
 * @author xiaomanwang
 *
 */
public class CountNegatives {
    public static int countNegatives(int[][] grid) {
        int result = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                if(grid[i][j] < 0) {
                    result += 1;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(countNegatives(new int[][] {{3,2},{1,0},{-1,2}}));
    }
}
