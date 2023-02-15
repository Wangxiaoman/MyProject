package leecode;

public class QuickSort {

    public static void quickSort(int[] array, int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int first = l, last = r - 1;
        int key = array[first];
        while (first < last) {
            while (first < last && array[last] >= key) {
                last--;
            }
            array[first] = array[last];

            while (first < last && array[first] <= key) {
                first++;
            }
            array[last] = array[first];
        }
        array[first] = key;
        quickSort(array, l, first);
        quickSort(array, first + 1, r);
    }


    public static void qs(int[] array, int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int first = l;
        int last = r - 1;

        int key = array[first];
        while (first < last) {
            while (first < last && array[last] >= key) {
                last--;
            }
            array[first] = array[last];
            while (first < last && array[first] <= key) {
                first++;
            }
            array[last] = array[first];
        }
        array[first] = key;
        qs(array, l, first);
        qs(array, first + 1, r);
    }

    public static void main(String[] args) {
        int[] array = new int[] {1, 4, 1, 5, 2, 6, 1};
        qs(array, 0, 7);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }
}
