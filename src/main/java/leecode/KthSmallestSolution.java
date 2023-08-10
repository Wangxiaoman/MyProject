package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author xiaomanwang
 * 给定一个每行中的元素个数不等的序列，其中同一行的元素单调递增。
 * 请在这个特定序列中找出第 K 小的数。
 */
public class KthSmallestSolution {
    /**
     * @param arr: an array of integers
     * @param k: an integer
     * @return: the Kth smallest element in a specific array
     */
    public static int kthSmallest(int[][] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
        }
        Collections.sort(list);
        return list.get(k-1);
    }
    
    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][] {{1, 5, 7, 9},{3, 4},{2, 7, 8}}, 5));
    }
}
