package leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StringCompressSolution {
    private String originStr;
    private int currentIndex = 0;

    public StringCompressSolution(String compressedStr) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<compressedStr.length();i+=2) {
            char c = compressedStr.charAt(i);
            int size = compressedStr.charAt(i+1) - '0';
            for(int j=0;j<size;j++) {
                sb.append(c);
            }
        }
        this.originStr = sb.toString();
    }

    public char next() {
        if(this.currentIndex >= this.originStr.length()) {
            return '#';
        }
        return this.originStr.charAt(currentIndex++);
    }

    public boolean hasNext() {
        if(this.currentIndex < this.originStr.length() - 1) {
            return true;
        }
        return false;
    }
    
    public static int largestUniqueNumber(int[] nums) {
        // write your code here.
        if(nums.length <= 1){
            return nums[0];
        }
        Arrays.sort(nums);
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        for(int i=nums.length-1; i>=1; i--){
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i],count);
            if(nums[i] != nums[i-1]){
                if(count == 1){
                    return nums[i];
                }
            }
        }
        return nums[nums.length-1];
    }
    
    public static boolean isSplitable(String s) {
        // --- write your code here ---
        int length2 = 0;
        StringBuffer sb = new StringBuffer();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++){
            char cp = s.charAt(i-1);
            char c = s.charAt(i);
            if(cp == c) {
                sb.append(c);
                if(i == s.length() -1) {
                    int lastLength = sb.length() % 3;
                    if(lastLength ==1) {
                        return false;
                    }
                    if(lastLength == 2) {
                        length2++;
                    }
                }
            } else {
                int lastLength = sb.length() % 3;
                if(lastLength ==1) {
                    return false;
                }
                if(lastLength == 2) {
                    length2++;
                }
                if(length2 > 1) {
                    return false;
                }
                sb = new StringBuffer();
                sb.append(c);
            }
        }
        if(length2 != 1) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(largestUniqueNumber(new int[] {3,5,1,7,9,1,9}));
        System.out.println(isSplitable("00011111222"));
    }
}
