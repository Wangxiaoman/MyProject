package leecode;

public class CountSquaresSolution {
    public static int countSquares(int[][] matrix) {
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int size = Math.min(matrix.length - i, matrix[0].length - j);
                for (int s = 0; s < size; s++) {
                    if (isSquares(matrix, i, j, s)) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private static boolean isSquares(int[][] matrix, int x, int y, int size) {
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (matrix[x + i][y + j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] data = new int[][] {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println(countSquares(data));
    }
}
