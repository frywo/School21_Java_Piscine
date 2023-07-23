import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int counter = 0;
        int sum;
        Scanner  sc = new Scanner(System.in);
        int Digit = sc.nextInt();
        while (Digit!=42){
            sum = sumDigit(Digit);
            counter += PrimeDigitCheck(sum);
            Digit = sc.nextInt();
        }
        System.out.print("Count of coffee-request – " + counter);

    }
    private static int PrimeDigitCheck (int primeDigit){
        int max = sqrt(primeDigit);
        int counter = 2;
        while (counter<=max){
            if(primeDigit%counter==0){
                return 0;
            }
            ++counter;
        }

        return 1;
    }
    private static int sqrt(int digit){
        int i = 0;
        while (i*i <= digit){
            ++i;
        }
        return i;
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