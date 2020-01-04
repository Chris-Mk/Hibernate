import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeFactors {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        System.out.println(getPrimeFactors(number));
    }

    private static List<Integer> getPrimeFactors(int number) {
        List<Integer> primeFactors = new ArrayList<>();

        for (int i = 2; i <= number; i++) {
            if (number % i == 0 && isPrime(i)) {
                primeFactors.add(i);
            }
        }
        return primeFactors;
    }

    private static boolean isPrime(int i) {
        int divider = 2;
        int maxDivider = (int) Math.sqrt(i);

        while (divider <= maxDivider) {
            if (i % divider == 0) {
                return false;
            }
            divider++;
        }
        return true;
    }
}
