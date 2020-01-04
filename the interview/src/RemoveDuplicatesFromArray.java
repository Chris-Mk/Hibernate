import java.util.Arrays;

public class RemoveDuplicatesFromArray {
    public static void main(String[] args) {

        int[][] test = new int[][]{
                {1, 1,  2, 2, 3, 4, 5, 4, 6, 1, 1, 1, 5, 5},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 1, 1, 1, 1, 1},
        };

        for (int[] ints : test) {
            System.out.println(Arrays.toString(removeDuplicates(ints)));
        }
    }

    private static int[] removeDuplicates(int[] ints) {
//        return Arrays.stream(ints)
//                .distinct()
//                .sorted()
//                .toArray();

        int length = ints.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (ints[i] == ints[j]) {
                    ints[j] = ints[length - 1];
                    length--;
                    j--;
                }
            }
        }

        final int[] copy = Arrays.copyOf(ints, length);
        Arrays.sort(copy);

        return copy;
    }
}
