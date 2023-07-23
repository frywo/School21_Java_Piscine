public class IllegalTransactionException extends RuntimeException{
    @Override
    public String toString() {
        return "Not enough money";
    }
}
