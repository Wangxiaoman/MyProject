package leecode;

// 88. 合并两个有序数组
public class MergeSortedArray {
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m+n];
        int index1 = 0;
        int index2 = 0;
        int indexr = 0;
        
        while(true) {
            if(index1 == m && index2 ==n ) {
                break;
            }
            if(index1 == m) {
                result[indexr] = nums2[index2];
                index2++;
                indexr++;
                continue;
            }
            if(index2 == n) {
                result[indexr] = nums1[index1];
                index1++;
                indexr++;
                continue;
            }
            
            if(nums1[index1] <= nums2[index2]) {
                result[indexr] = nums1[index1];
                index1++;
            } else {
                result[indexr] = nums2[index2];
                index2++;
            }
            indexr++;
        }
        
        for(int i=0;i<nums1.length;i++) {
            nums1[i] = result[i];
        }
    }
    
    public static void main(String[] args) {
        int[] nums1 = new int[] {1,3,4,6,8};
        int[] nums2 = new int[] {2,3,5,7};
        merge(nums1, 5, nums2, 4);
        
        for(int i: nums1) {
            System.out.print(i + ",");
        }
    }
}
