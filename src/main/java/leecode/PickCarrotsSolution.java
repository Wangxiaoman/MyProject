package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaomanwang
 * 给定一个n * m 的矩阵 carrot, carrot[i][j] 表示(i, j) 坐标上的胡萝卜数量。
 * 从矩阵的中心点出发，每一次移动都朝着四个方向中胡萝卜数量最多的方向移动，保证移动方向唯一。
 * 返回你可以得到的胡萝卜数量。
 */
public class PickCarrotsSolution {
    /**
     * @param carrot: an integer matrix
     * @return: Return the number of steps that can be moved.
     */
    public static int pickCarrots(int[][] carrot) {
        // write your code here
        int x = (carrot.length-1)/2;
        int y = (carrot[0].length-1)/2;
        int sum = carrot[x][y];
        System.out.println(sum);
        carrot[x][y] = 0;
        Point p = null;
        do {
            p = pickCarrots(carrot, x , y);
            if(p != null) {
                System.out.println("x:"+p.x+"y:"+p.y+",num:"+carrot[p.x][p.y]);
                sum += carrot[p.x][p.y];
                carrot[p.x][p.y] = 0;

                x = p.x;
                y = p.y;
            }
        }while(p != null);
        return sum;
    }
    
    public static Point pickCarrots(int[][] carrot, int x, int y) {
        List<Point> ps = getPointAroundPoints(x, y, carrot.length, carrot[0].length);
        int max = 0;
        Point maxNumPoint = null;
        for(Point p : ps) {
            if(carrot[p.x][p.y] > max) {
                max = carrot[p.x][p.y];
                maxNumPoint = p;
            }
        }
        return maxNumPoint;
    }
    
    public static List<Point> getPointAroundPoints(int x, int y, int xMax, int yMax) {
        List<Point> result = new ArrayList<>();
        if(y-1 >=0) result.add(new Point(x,y-1));
        if(y+1 <yMax) result.add(new Point(x,y+1));
        if(x-1 >=0) result.add(new Point(x-1,y));
        if(x+1 <xMax) result.add(new Point(x+1,y));
        return result;
    }
    
    static class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) {
//        int[][] nums = new int[][] {{5, 7, 6, 3},{2,  4, 8, 12},{3, 5, 10, 7},{4, 16, 4, 17}};
//        int[][] nums = new int[][] {{1,1,2,3,4}};
        int[][] nums = new int[][] {{5,7,6,3},{2,4,8,12},{3,5,10,7},{4, 16, 4, 17}};
        int result = pickCarrots(nums);
        System.out.println(result);
    }
}


