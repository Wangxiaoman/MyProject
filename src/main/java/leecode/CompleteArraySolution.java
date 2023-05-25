package leecode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaomanwang
 *  在本题中，你需要验证一个数组是不是完整的数组。
    完整的数组定义为：存在一个长度为 n 的数组，当且仅当这个数组包含 1 到 n 所有的值时，
    我们称之为完整的数组。
 */
public class CompleteArraySolution {
    /**
     * @param arr: array
     * @return: return true if it's a complete array, otherwise false
     */
    public boolean isCompleteArray(List<Integer> arr) {
        Set<Integer> numSet = new HashSet<>();
        for(int n : arr) {
            if(n > arr.size() || n <= 0) {
                return false;
            }
            if(numSet.contains(n)) {
                return false;
            }
            numSet.add(n);
        }
        return true;
    }
}
