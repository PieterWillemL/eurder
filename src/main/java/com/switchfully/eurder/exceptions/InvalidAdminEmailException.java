package com.switchfully.eurder.exceptions;

public class InvalidAdminEmailException extends RuntimeException{

    public InvalidAdminEmailException(){
        super("The provided email is not an Admin email. Access denied.");
    }
}
