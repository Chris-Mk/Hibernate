import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {

        int[] array = {2, 3, 5, 35, 45, 60, 320};

        Scanner scanner = new Scanner(System.in);
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println(binarySearch(array, key));
    }

    private static int binarySearch(int[] array, int key) {
        int startIndex = 0, endIndex = array.length - 1;

        while (startIndex <= endIndex) {
            int midIndex = (startIndex + endIndex) / 2;

            if (array[midIndex] > key) {
                endIndex =  midIndex - 1;
            } else if (array[midIndex] < key) {
                startIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
}
