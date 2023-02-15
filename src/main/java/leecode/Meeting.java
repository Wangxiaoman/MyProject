package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meeting {
    private static class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return true;
        }
        Collections.sort(intervals, (o1, o2) -> {
            return o1.start - o2.start;
        });
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0,20));
        intervals.add(new Interval(21,30));
        intervals.add(new Interval(31,50));
        intervals.add(new Interval(80,100));
        System.out.println(canAttendMeetings(intervals));
    }
}
