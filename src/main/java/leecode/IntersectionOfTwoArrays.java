/**
 * 
 */
package leecode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author linkedme Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 *
 */
public class IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        for(int i=0;i<nums1.length;i++){
            nums1Set.add(nums1[i]);
        }
        Set<Integer> nums2Set = new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            if(nums1Set.contains(nums2[i])){
                nums2Set.add(nums2[i]);
            }
        }
        
        int[] result = new int[nums2Set.size()];
        int index = 0;
        Iterator<Integer> iter = nums2Set.iterator();
        while(iter.hasNext()){
            result[index++] = iter.next();
        }
        return result;
    }
    

    public static void main(String[] args) {
        int[] result = intersection(new int[]{1,2,3,1},new int[]{1,3,5,6,1});
        for(int x : result){
            System.out.print(x+",");
        }
    }
}
