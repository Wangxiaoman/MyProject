package leecode;

public class CycleCount {
    public static int[] countPoints(int[][] points, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            int inCount = 0;
            int xx = query[0];
            int yy = query[1];
            int r = query[2];

            for (int[] point : points) {
                int x = point[0];
                int y = point[1];
                
                if((x-xx)*(x-xx) + (y-yy)*(y-yy) <= r*r) {
                    inCount = inCount + 1;
                }
                
            }
            result[i] = inCount;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{{1,3},{3,3},{5,3},{2,2}};
        int[][] queries = new int[][] {{2,3,1},{4,3,1},{1,1,2}};
        System.out.println(countPoints(points,queries));
    }
}
