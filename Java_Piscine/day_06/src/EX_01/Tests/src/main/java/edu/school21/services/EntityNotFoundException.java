package edu.school21.services;

public class EntityNotFoundException extends RuntimeException{
    @Override
    public String toString() {
        return "It's login doesn't exists!";
    }
}
