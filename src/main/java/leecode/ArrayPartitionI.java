/**
 * 
 */
package leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author linkedme 561
 *
 */
public class ArrayPartitionI {
    public static int arrayPairSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(Integer num : nums){
            list.add(num);
        }
        Collections.sort(list);
        
        int result = 0;
        for (int i = 0; i < list.size() ; i = i+2) {
            result = result + list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[]{1,4,3,2}));
        System.out.println(arrayPairSum(new int[]{1,1,2,2}));
    }
}
