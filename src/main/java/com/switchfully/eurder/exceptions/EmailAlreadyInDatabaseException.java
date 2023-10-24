package com.switchfully.eurder.exceptions;

public class EmailAlreadyInDatabaseException extends RuntimeException{

    public EmailAlreadyInDatabaseException(String email){
        super("A Customer Account for " + email + " already exists.");
    }
}
