import java.util.Scanner;

public class ReverseStringWithRecursion {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(reverseString(input));
    }

    private static String reverseString(String input) {
        if (input.length() < 2) {
            return input;
        }

        return reverseString(input.substring(1)) + input.charAt(0);
    }
}
