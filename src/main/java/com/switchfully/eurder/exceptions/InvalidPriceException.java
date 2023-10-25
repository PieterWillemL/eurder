package com.switchfully.eurder.exceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(){
        super("Price cannot be negative");
    }
}
