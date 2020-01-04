import java.util.Arrays;
import java.util.Collections;

public class ReverseArray {
    public static void main(String[] args) {

        int[][] test = new int[][]{
                {1, 1,  2, 2, 3, 4, 5, 4, 6, 1, 1, 1, 5, 5},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 1, 1, 1, 1, 1},
        };

        for (int[] ints : test) {
            System.out.println(Arrays.toString(reverseArrayInPlace(ints)));
        }
    }

    private static int[] reverseArray(int[] ints) {
        int[] reversed = new int[ints.length];

        for (int i = 0; i < ints.length; i++) {
            reversed[i] = ints[ints.length - 1 - i];
        }
        return reversed;
    }

    private static int[] reverseArrayInPlace(int[] ints) {
        for (int i = 0; i < ints.length / 2; i++) {
            int temp = ints[i];
            ints[i] = ints[ints.length - 1 - i];
            ints[ints.length - 1 - i] = temp;
        }
        return ints;
    }
}
