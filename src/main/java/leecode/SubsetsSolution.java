package leecode;

import java.util.ArrayList;
import java.util.List;
/**
 * @author xiaomanwang
 * 
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
    说明：解集不能包含重复的子集。
    示例:
     输入： nums = [1,2,3]
     输出：
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]

 *
 */
public class SubsetsSolution {
    
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for(int num :  nums){
            List<List<Integer>> newList = new ArrayList<>();
            if(!result.isEmpty()) {
                for(List<Integer> rl : result) {
                    List<Integer> nl = new ArrayList<>(rl);
                    nl.add(num);
                    newList.add(nl);
                }
            }
            if(!newList.isEmpty()) {
                result.addAll(newList);
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(num);
            result.add(temp);
        }

        result.add(new ArrayList<>());
        return result;
    }
    
    public static void main(String[] args) {
        List<List<Integer>> result = subsets(new int[] {1,2,3});
        for(List<Integer> l : result) {
            System.out.println(l);
        }
    }
}
