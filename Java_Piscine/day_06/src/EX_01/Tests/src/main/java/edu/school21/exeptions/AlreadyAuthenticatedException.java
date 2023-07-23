package edu.school21.exeptions;

public class AlreadyAuthenticatedException extends RuntimeException{
    @Override
    public String toString() {
        return "This user already authenticated";
    }
}
