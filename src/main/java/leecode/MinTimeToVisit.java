package leecode;

public class MinTimeToVisit {
    public static int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        for (int i = 1; i < points.length; i++) {
            int[] tempPoint = points[i-1];
            int[] currentPoint = points[i];
            int stepX = currentPoint[0] > tempPoint[0] ? (currentPoint[0] - tempPoint[0])
                    : (tempPoint[0] - currentPoint[0]);
            int stepY = currentPoint[1] > tempPoint[1] ? (currentPoint[1] - tempPoint[1])
                    : (tempPoint[1] - currentPoint[1]);

            result += (stepY > stepX) ? stepY : stepX;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = new int[][] {{1, 1}, {3, 4}, {-1, 0}};
        System.out.println(minTimeToVisitAllPoints(points));
        points = new int[][] {{3, 2}, {-2, 2}};
        System.out.println(minTimeToVisitAllPoints(points));
    }
}
