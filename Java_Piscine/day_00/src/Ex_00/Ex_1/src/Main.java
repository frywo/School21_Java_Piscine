public class Main {
    public static void main(String[] args) {
        int digit = 479598;
        System.out.print(sumDigit(digit));
    }
    private static int sumDigit (int data) {
        int sum = 0;
        while (data >0) {
            sum += data%10;
            data = data/10;
        }
        return sum;
    }
}