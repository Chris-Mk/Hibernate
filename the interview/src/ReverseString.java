import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());

        System.out.println(reverseStr(str, k));
    }

    private static String reverseStr(String s, int k) {
        StringBuilder builder = new StringBuilder();
        int start = 0, end = 2 * k;

        while (start < s.length()) {
            String subStr = s.substring(start, Math.min(start + end, s.length()));

            if (subStr.length() < k) {
                builder.append(reverseInPlace(subStr));
            } else {
                builder.append(reverseInPlace(subStr.substring(0, k)))
                        .append(subStr.substring(k));
            }
            start += end;
        }
        return builder.toString();
    }

    private static String reverse(String subString) {
        StringBuilder builder = new StringBuilder();

        for (int i = subString.length() - 1; i >= 0; i--) {
            builder.append(subString.charAt(i));
        }

        return builder.toString();
    }

    private static String reverseRecursively(String subString) {
        if (subString.length() < 2) {
            return subString;
        }

        return reverseRecursively(subString.substring(1)) + subString.charAt(0);
    }

    private static String reverseInPlace(String s) {
        final char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }

        return new String(chars);
    }
}
