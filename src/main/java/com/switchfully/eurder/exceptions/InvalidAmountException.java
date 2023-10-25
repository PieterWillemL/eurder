package com.switchfully.eurder.exceptions;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(){
        super("Amount of items can not be lower than zero");
    }
}
