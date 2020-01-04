import java.util.Arrays;
import java.util.Scanner;

public class IntegersSumEqualToK {
    public static void main(String[] args) {

        int[][] test = new int[][]{
                {1, 1,  2, 2, 3, 4, 5, 4, 6, 1, 1, 1, 5, 5},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 1, 1, 1, 1, 1},
        };

        Scanner scanner = new Scanner(System.in);
        int k =  Integer.parseInt(scanner.nextLine());

        for (int[] ints : test) {
            getPairs(ints, k);
        }
    }

    private static void getPairs(int[] ints, int k) {
        Arrays.sort(ints);

        int left = 0, right = ints.length - 1;
        while (left < right) {
            int sum = ints[left] + ints[right];

            if (sum == k) {
                System.out.printf("(%d, %d)%n", ints[left], ints[right]);
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
    }
}
