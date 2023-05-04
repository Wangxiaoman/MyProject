package leecode;

public class ArrayGameSolution {
    /**
     * @param arr: the array
     * @return: determine the number of moves to make all elements equals
     *          给定一个整数数组，请算出让所有元素相同的最小步数。每一步你可以选择一个元素，使得其他元素全部加1。
     * 
     */

    public static long arrayGame(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        long result = 0;
        for (int a : arr) {
            result = result + (a - min);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(arrayGame(new int[] {3, 4, 6, 6, 3}));
    }
}
