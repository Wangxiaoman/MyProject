/**
 * 
 */
package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author linkedme Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 *
 */
public class IntersectionOfTwoArrays2 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer,Integer> nums1Map = new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            int size = nums1Map.getOrDefault(nums1[i], 0);
            nums1Map.put(nums1[i], size+1);
        }
        System.out.println(nums1Map);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<nums2.length;i++){
            Integer value = nums1Map.get(nums2[i]);
            
            if(value != null && value > 0){
                list.add(nums2[i]);
                nums1Map.put(nums2[i], value-1);
            }
        }
        
        int[] result = new int[list.size()];
        int index = 0;
        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()){
            result[index++] = iter.next();
        }
        return result;
    }
    

    public static void main(String[] args) {
        int[] result = intersection(new int[]{1,1},new int[]{1});
        for(int x : result){
            System.out.print(x+",");
        }
    }
}
