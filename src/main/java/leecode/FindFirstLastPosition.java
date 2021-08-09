package leecode;

// 34. 在排序数组中查找元素的第一个和最后一个位置
public class FindFirstLastPosition {
    
    public static int[] searchRange(int[] nums, int target) {
        int index = getIndex(nums, target);
        if(index == -1) {
            return new int[] {-1,-1};
        } else {
            
            int b = index;
            int e = index;
            int length = nums.length;
            if(length == 1) {
                return new int[] {0, nums.length -1};
            }
            
            while(target == nums[b]) {
                b = b - 1;
                if(b == -1) {
                    break;
                }
            }
            
            while(target == nums[e]) {
                e = e + 1;
                if(e == length) {
                    break;
                }
            }
            return new int[] {b+1,e-1};
        }
    }
    
    public static int getIndex(int[] nums, int target) {
        int b = 0;
        int e = nums.length - 1;
        while(b <= e) {
            int mid = b + (e-b)/2;
            if(target == nums[mid]) {
                return mid;
            } else if(target < nums[mid]) {
                e = mid -1;
            } else {
                b = mid + 1;
            }
        }
        return -1;
    }
    
    public static int[] searchLeftRight(int[] nums, int target) {
        int left = searchLeftOrRight(nums, target, true);
        int right = searchLeftOrRight(nums, target, false);
        return new int[] {left,right};
    }
    
    public static int searchLeftOrRight(int[] nums, int target, boolean leftRight) {
        int b = 0;
        int e = nums.length - 1;
        int result = -1;
        while(b <= e) {
            int mid = b + (e-b)/2;   
            if(target < nums[mid]) {
                e = mid - 1; 
            } else if(target > nums[mid]) {
                b = mid + 1;
            } else {
                result = mid;
                if(leftRight) {
                    e = mid - 1;
                } else {
                    b = mid + 1;
                }
            }
        }
        return result;
    }
    
    
    public static void main(String[] args) {
//        int[] nums = new int[] {1,2,2,2,3,4,5,6};
//        int[] nums = new int[] {2};
//        int[] result = searchRange(nums,2);
//        System.out.println(result[0] + "," + result[1]);
//        int[] result = searchLeftRight(new int[] {1,2,2,2,3,4,5,6}, 2);
        int[] result = searchLeftRight(new int[] {2}, 2);
        System.out.println(result[0] + "," + result[1]);
    }
}
