import java.util.Scanner;

public class BinarySubStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(queryString(str, n));
    }

    private static boolean queryString(String s, int n) {
        boolean hasAllSubStrings = true;

        for (int i = 1; i <= n; i++) {
            final String binaryString = Integer.toBinaryString(i);

            if (!s.contains(binaryString)) {
                hasAllSubStrings = false;
                break;
            }
        }
        return hasAllSubStrings;
    }
}
