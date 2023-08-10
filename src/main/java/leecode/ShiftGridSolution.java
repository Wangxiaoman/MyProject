package leecode;

public class ShiftGridSolution {
    /**
     * @param grid: m*n matrix
     * @param k: Number of shifts
     * @return: m*n matrix after shift
     */
    public static int[][] shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int length = grid.length * grid[0].length;
        
        // 2维 -> 1维
        int[] nums = new int[length];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[index++] = grid[i][j];
            }
        }
        
        // 移动
        int moveIndex = k % length;
        int[] shiftedGrid = new int[length];
        for (int i = 0; i < length; i++) {
            int moveCurrentIndex = (moveIndex + i) % length;
            shiftedGrid[moveCurrentIndex] = nums[i];
        }
        
        // 1维 -> 2维
        int[][] result = new int[m] [n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = shiftedGrid[i*n+j];
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        int[][] result = shiftGrid(grid, 3);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j]+",");
            }
            System.out.println();
        }
    }
}
