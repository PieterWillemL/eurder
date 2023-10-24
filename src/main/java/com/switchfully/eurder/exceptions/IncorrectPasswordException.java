package com.switchfully.eurder.exceptions;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String email){
        super("Wrong password given for: " + email);
    }
}
