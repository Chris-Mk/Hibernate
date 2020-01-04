import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        System.out.println(Arrays.toString(findOccurrences(text, first, second)));
    }

    private static String[] findOccurrences(String text, String first, String second) {
        List<String> foundWords = new ArrayList<>();
        final String[] words = text.split("\\s+");

        for (int i = 0; i < words.length - 2; i++) {
            if (words[i].equals(first) && words[i + 1].equals(second)) {
                foundWords.add(words[i + 2]);
            }
        }
        return foundWords.toArray(String[]::new);
    }
}
