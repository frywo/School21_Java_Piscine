import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner  sc = new Scanner(System.in);
       int primeDigit = sc.nextInt();
       moreThenTwo(primeDigit);
       primeDigitCheck(primeDigit);
    }
    private static void primeDigitCheck (int primeDigit){
        int max = sqrt(primeDigit);
        int counter = 2;
        while (counter<=max){
            if(primeDigit%counter==0){
                System.out.print("false " + (counter-1));
                System.exit(0);
            }
            ++counter;
        }
        System.out.print("true " + (counter-2));
    }
    private static void moreThenTwo(int primeDigit){
        if (primeDigit<2){
            System.err.print("Illegal Argument");
            System.exit(-1);
        }
    }
    private static int sqrt(int digit){
        int i = 0;
        while (i*i <= digit){
            ++i;
        }
        return i;
    }
}