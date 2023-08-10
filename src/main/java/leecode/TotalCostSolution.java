package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xiaomanwang
 * 今天有N个面试者需要面试，公司安排了两个面试的城市A和B，
 * 每一个面试者都有到A城市的开销costA和到B城市的开销costB。
 * 公司需要将面试者均分成两拨，使得total cost最小。
 */
public class TotalCostSolution {
    /**
     * @param cost: The cost of each interviewer
     * @return: The total cost of all the interviewers.
     */
    public static int totalCost(List<List<Integer>> cost) {
        // write your code here
        List<CostMan> list = new ArrayList<>();
        for(List<Integer> c : cost) {
            list.add(new CostMan(c.get(0), c.get(1)));
        }
        
        Collections.sort(list, new Comparator<CostMan>() {
            @Override
            public int compare(CostMan o1, CostMan o2) {
                return (o1.costA - o1.costB) - (o2.costA - o2.costB);
            }});
        
        int sum = 0;
        for (int i = 0; i < cost.size() / 2; i++) {
            sum += list.get(i).costA + list.get(cost.size() - i -1).costB;
        }
        return sum;
    }
    
    private static class CostMan{
        public int costA;
        public int costB;
        public CostMan(int a, int b) {
            this.costA = a;
            this.costB = b;
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(5,4));
        list.add(Arrays.asList(3,6));
        list.add(Arrays.asList(1,8));
        list.add(Arrays.asList(3,9));
        
        System.out.println(totalCost(list));
    }
}
