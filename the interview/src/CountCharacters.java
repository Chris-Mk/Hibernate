import java.util.Scanner;

public class CountCharacters {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char character = scanner.nextLine().charAt(0);

        System.out.println(countCharacterOccurrence(input, character));
        System.out.println(countCharacterOccurrenceRecursively(input, character));
    }

    private static int countCharacterOccurrence(String input, char character) {
        int count = 0;

        for (char ch : input.toCharArray()) {
            if (ch == character) {
                count++;
            }
        }
        return count;
    }

    private static int countCharacterOccurrenceRecursively(String input, char character) {
        int count = 0;

        if (input.length() < 1) {
            return count;
        }

        if (input.charAt(0) == character) {
            count++;
        }

        return countCharacterOccurrenceRecursively(input.substring(1), character) + count;
    }
}
