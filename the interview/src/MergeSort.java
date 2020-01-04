import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

//        int[] array = {2, 3, 5, 35, 45, 60, 320};
        int[] numbers = {3, 60, 35, 2, 45, 320, 5};

        MyMergeSort<Integer> mergeSort = new MyMergeSort<>();
        final Integer[] sort = mergeSort.sort(Arrays.stream(numbers).boxed().toArray(Integer[]::new));
        System.out.println(Arrays.toString(sort));

//        String[] strs = {"Java", "Mava", "Paja", "Wali", "Kava"};
//        MyMergeSort<String> stringMyMergeSort = new MyMergeSort<>();
//        final String[] sortedString = stringMyMergeSort.sort(strs);
//        System.out.println(Arrays.toString(sortedString));
//        final int[] sortedArray = mergeSort(numbers);
//        System.out.println(Arrays.toString(sortedArray));
    }

    private static int[] mergeSort(int[] array) {
        if (array.length == 1) return array;

        int firstPartitionLength = array.length / 2;
        int secondPartitionLength = array.length - firstPartitionLength;

        int[] firstPartitionArray = new int[firstPartitionLength];
        int[] secondPartitionArray = new int[secondPartitionLength];

        System.arraycopy(array, 0, firstPartitionArray, 0, firstPartitionLength);

        if (array.length - firstPartitionLength >= 0)
            System.arraycopy(array, firstPartitionLength, secondPartitionArray, 0, array.length - firstPartitionLength);

        mergeSort(firstPartitionArray);
        mergeSort(secondPartitionArray);

        int mainIndex = 0, firstPartitionIndex = 0, secondPartitionIndex = 0;

        while (firstPartitionIndex < firstPartitionLength && secondPartitionIndex < secondPartitionLength) {
            array[mainIndex++] = firstPartitionArray[firstPartitionIndex] < secondPartitionArray[secondPartitionIndex] ?
                    firstPartitionArray[firstPartitionIndex++] :
                    secondPartitionArray[secondPartitionIndex++];
        }

        while (firstPartitionIndex < firstPartitionLength) {
            array[mainIndex++] = firstPartitionArray[firstPartitionIndex++];
        }

        while (secondPartitionIndex < secondPartitionLength) {
            array[mainIndex++] = secondPartitionArray[secondPartitionIndex++];
        }

        return array;
    }
}
