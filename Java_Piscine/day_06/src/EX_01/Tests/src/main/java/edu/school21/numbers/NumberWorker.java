package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) throws IllegalNumberException{
        if(number<2){
            throw new IllegalNumberException();
        }
        int i = 2;
        while (i< Math.sqrt(number)+1){
            if(number%i==0){
                return false;
            }
            i++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;

        while (number!=0){
            sum+=number%10;
            number/=10;
        }
        return sum;
    }
}
