import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {

        int[] array = {2, 3, 5, 35, 45, 60, 320};
        int[] numbers = {3, 60, 35, 2, 45, 320, 5};

        quickSort(numbers, 0, numbers.length - 1);
        System.out.println(Arrays.toString(numbers));
//        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivot = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivot - 1);
            quickSort(array, pivot, endIndex);
        }
    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];

        while (startIndex <= endIndex) {
            while (array[startIndex] < pivot) {
                startIndex++;
            }

            while (array[endIndex] > pivot) {
                endIndex--;
            }

            if (startIndex <= endIndex) {
                int temp = array[startIndex];
                array[startIndex] = array[endIndex];
                array[endIndex] = temp;
                startIndex++;
                endIndex--;
            }
        }

        return startIndex;
    }
}
