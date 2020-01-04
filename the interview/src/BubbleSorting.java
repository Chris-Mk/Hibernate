import java.util.Arrays;

public class BubbleSorting {
    public static void main(String[] args) {

//        int[] numbers = {3, 60, 35, 2, 45, 320, 5};
        int[] numbers = {2, 3, 5, 35, 45, 60, 320};


        for (int i = 0; i < numbers.length; i++) {
            boolean hasSwapped = false;

            for (int j = 1; j < numbers.length - i; j++) {
                if (numbers[j - 1] > numbers[j]) {
                    int temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                    hasSwapped = true;
                }
            }

            if (!hasSwapped) break;
        }

//        int lastIndex = numbers.length - 1;
//
//        while (isSwapped) {
//            isSwapped = false;
//
//            for (int i = 0; i < lastIndex; i++) {
//                if (numbers[i] > numbers[i + 1]) {
//                    int temp = numbers[i];
//                    numbers[i] = numbers[i + 1];
//                    numbers[i + 1] = temp;
//                    isSwapped = true;
//                }
//            }
//            lastIndex--;
//        }

//        for (int i = 0; i < numbers.length; i++) {
//            for (int j = i + 1; j < numbers.length; j++) {
//                if (numbers[i] > numbers[j]) {
//                    int temp = numbers[i];
//                    numbers[i] = numbers[j];
//                    numbers[j] = temp;
//                }
//            }
//        }

        System.out.println(Arrays.toString(numbers));
    }
}
