package com.switchfully.eurder.exceptions;

public class InvalidCustomerEmailException extends RuntimeException {

    public InvalidCustomerEmailException() {

        super("The provided email is not a Customer email. Access denied.");
    }
}
