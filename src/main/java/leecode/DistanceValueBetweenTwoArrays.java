package leecode;

// 1385. 两个数组间的距离值
public class DistanceValueBetweenTwoArrays {
    public static int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int counter = 0;
        for (int i = 0; i < arr1.length; i++) {
            boolean rightValue = true;
            for (int j = 0; j < arr2.length; j++) {
                int temp = arr2[j] > arr1[i] ? (arr2[j] - arr1[i]) : (arr1[i] - arr2[j]);
                if (temp <= d) {
                    rightValue = false;
                    break;
                }
            }
            if (rightValue) {
                counter += 1;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(findTheDistanceValue(new int[] {4, 5, 8}, new int[] {10, 9, 1, 8}, 2));
    }
}
