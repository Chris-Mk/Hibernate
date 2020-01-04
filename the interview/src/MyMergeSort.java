public class MyMergeSort<T> {
    private T[] array;

    public T[] sort(T[] array) {
        if (array.length == 1 || array.length == 0) {
            return array;
        }

        return mergeSort(array);
    }

    @SuppressWarnings("unchecked")
    private T[] mergeSort(T[] array) {
        if (array.length == 1) return array;

        int firstPartitionLength = array.length / 2;
        int secondPartitionLength = array.length - firstPartitionLength;

        T[] firstPartitionArray = (T[]) new Object[firstPartitionLength];
        T[] secondPartitionArray = (T[]) new Object[secondPartitionLength];

        for (int i = 0; i < firstPartitionLength; i++) {
            firstPartitionArray[i] = array[i];
        }

        for (int i = firstPartitionLength; i < array.length; i++) {
            secondPartitionArray[i - firstPartitionLength] = array[i];
        }

        mergeSort(firstPartitionArray);
        mergeSort(secondPartitionArray);

        int mainIndex = 0, firstPartitionIndex = 0, secondPartitionIndex = 0;

        while (firstPartitionIndex < firstPartitionLength && secondPartitionIndex < secondPartitionLength) {
//            if (firstPartitionArray[firstPartitionIndex] < secondPartitionArray[secondPartitionIndex]) {
//                array[mainIndex++] = firstPartitionArray[firstPartitionIndex++];
//            } else {
//                array[mainIndex++] = secondPartitionArray[secondPartitionIndex++];
//            }
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
