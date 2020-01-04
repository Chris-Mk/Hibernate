public class MaxAndMinValues {
    public static void main(String[] args) {

        int[][] test = new int[][]{
                {1, 1,  2, 2, 3, 4, 5, 4, 6, 1, 1, 1, 5, 5},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 1, 1, 1, 1, 1},
        };

        for (int[] ints : test) {
            final int[] maxAndMinValues = getMaxAndMinValues(ints);
            System.out.println("Min: " + maxAndMinValues[0]);
            System.out.println("Max: " + maxAndMinValues[1]);
        }
    }

    private static int[] getMaxAndMinValues(int[] ints) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int anInt : ints) {
            if (anInt < minValue) {
                minValue = anInt;
            } else if (anInt > maxValue) {
                maxValue = anInt;
            }
        }

        return new int[] {minValue, maxValue};
    }
}
