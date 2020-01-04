public class SwapIntegerWithoutVariable {
    public static void main(String[] args) {

        int a = Integer.MAX_VALUE;
        int b = 10;
//        a = a + b;
//        b = a - b;
//        a = a - b;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);
    }
}
