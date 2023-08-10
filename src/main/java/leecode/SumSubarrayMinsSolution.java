package leecode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaomanwang
 * 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
 * 由于答案可能很大，因此模 10^9 + 7以后返回答案。
 */
public class SumSubarrayMinsSolution {
    /**
     * @param a: an array
     * @return: the sum of subarray minimums
     */
    public static int sumSubarrayMins(int[] a) {
        // Write your code here.
        List<Integer> list =  Arrays.stream(a).boxed().collect(Collectors.toList());
        long sum = 0;
        for (int step = 1; step <= list.size(); step++) {
            for (int index = 0; index < list.size(); index++) {
                if(index + step > list.size()) {
                    break;
                }
                int end = Math.min(index + step, list.size());
                List<Integer> sublist = list.subList(index, end);
                System.out.println("step="+step+",index="+index+",sublist:"+sublist);
                int min = minArrays(sublist);
                System.out.println("min:"+min);
                sum += min;
            }
        }
        return (int) (sum %(Math.pow(10, 9)+7));
    }
    
    public static int minArrays(List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        int tmp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < tmp) {
                tmp = list.get(i);
            }
        }
        return tmp;
    }
    
    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[] {3,1,2,4}));
    }
}
