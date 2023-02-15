package leecode;

import java.util.Arrays;
import java.util.Comparator;

// 435. 无重叠区间
public class NonOverlappingIntervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        int counter = 0;
        int[] current = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] > intervals[i][0]) {
                counter++;
            } else {
                current = intervals[i];
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        // int[][] nums = new int[][] {{1,2}, {2,3}, {3,4}, {1,3}};
        // int[][] nums = new int[][] {{1,2}, {1,2}, {3,4}, {1,3}};
        // int[][] nums = new int[][] {{1,2}, {1,2}, {1,2}};
        int[][] nums = new int[][] {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49},
                {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};

        System.out.println(eraseOverlapIntervals(nums));
    }
}
