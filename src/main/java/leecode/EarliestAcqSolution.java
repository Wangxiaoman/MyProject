package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EarliestAcqSolution {
    
    public int earliestAcq(int[][] logs, int n) {
        List<Relation> list = new ArrayList<>();
        for(int[] log : logs) {
            list.add(new Relation(log[0], log[1], log[2]));
        }
        Collections.sort(list, new Comparator<Relation>() {
            @Override
            public int compare(Relation o1, Relation o2) {
                return o1.time - o2.time;
            }
        });
        
    }
    
    private static class Relation{
        int time;
        int fid;
        int tid;
        public Relation(int time, int fid, int tid) {
            this.tid = tid;
            this.time = time;
            this.fid = fid;
        }
    }
}
