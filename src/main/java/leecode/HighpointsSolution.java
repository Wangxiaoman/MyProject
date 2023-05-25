package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomanwang
 * 给一个n*m大小的矩阵，寻找矩阵中所有比邻居（上下左右，对角也算，不考虑边界，即8个）都严格大的点。
 * 返回一个n*m大小的矩阵，如果原矩阵中的点比邻居都严格大，则该位置为1，反之为0。
 */
public class HighpointsSolution {
    /**
     * @param grid: a matrix
     * @return: Find all points that are strictly larger than their neighbors
     */
    public static int[][] highpoints(int[][] grid) {
        int[][] result = new int[grid.length][grid[0].length];
        // write your code here
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int num = grid[i][j];
                List<Point> aroudPoints = getPointAroundPoints(i, j);
                if(aroudPoints.size() > 0) {
                    int t = 1;
                    for(Point p : aroudPoints) {
                        if(p.x >= 0 && p.x <grid.length && p.y >= 0 && p.y < grid[0].length && num <= grid[p.x][p.y]) {
                            t = 0;
                        }
                    }
                    result[i][j] = t;
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }
    
    public static List<Point> getPointAroundPoints(int x, int y) {
        List<Point> result = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for(int j= -1; j < 2 ; j++) {
                if(!(i == 0 && j == 0)) {
                    result.add(new Point(i+x,j+y));
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[][] nums = new int[][] {{1,2,3,4},{5,5,5,2},{1,1,1,1},{0,0,0,9}};
        int[][] result = highpoints(nums);
        for(int i=0;i<result.length;i++) {
            for(int j=0;j<result[0].length;j++) {
                System.out.println(result[i][j]);
            }
        }
    }
}

class Point{
    public int x;
    public int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
