package leecode;
// 11. 盛最多水的容器
// 输入：[1,8,6,2,5,4,8,3,7] 
// 输出：49 
public class ContainerWithMostWater {
    
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i; j < height.length; j++) {
                int min = height[j]>height[i]? height[i]:height[j];
                int current = min * (j-i);
                if(current > max) {
                    max = current;
                }
            }
        }
        return max;
    }
    
    public static int maxArea1(int[] height) {
        int max = 0;
        int begin = 0;
        int end = height.length -1;
        while(end > begin) {
            int min = 0;
            int current = 0;
            if(height[end]>height[begin]) {
                min = height[begin];
                current = (end - begin) * min;
                begin ++;
            } else {
                min = height[end];
                current = (end - begin) * min;
                end --;
            }
            if(current > max) {
                max = current;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(maxArea1(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
