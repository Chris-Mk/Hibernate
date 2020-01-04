import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstNonRepeatingCharacters {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

//        System.out.println(getFirstNonRepeatingCharacters(input));
        System.out.println(getFirstNonRepeatingCharacter(input));
    }

    private static char getFirstNonRepeatingCharacter(String input) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();

        for (char ch : input.toCharArray()) {
            charCount.putIfAbsent(ch, 0);
            charCount.put(ch, charCount.get(ch) + 1);
        }

        for (Map.Entry<Character, Integer> characterIntegerEntry : charCount.entrySet()) {
            if (characterIntegerEntry.getValue() == 1) {
                return characterIntegerEntry.getKey();
            }
        }
        return 0;
    }

    private static String getFirstNonRepeatingCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            final String firstNonRepeatingCharacter = input.substring(0, i);

            if (firstNonRepeatingCharacter.contains(String.valueOf(input.charAt(i)))) {
                return firstNonRepeatingCharacter;
            }
        }
        return input;
    }
}
