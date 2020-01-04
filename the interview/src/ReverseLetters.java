public class ReverseLetters {
    public static void main(String[] args) {

        System.out.println(reverseOnlyLetters("a-bC-dEf-ghIj"));
    }

    private static String reverseOnlyLetters(String s) {
        StringBuilder builder = new StringBuilder();
        int index = s.length() - 1;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                char replacement = s.charAt(index);

                while (!Character.isLetter(replacement)) {
                    replacement = s.charAt(--index);
                }

                builder.append(replacement);
                index--;
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
