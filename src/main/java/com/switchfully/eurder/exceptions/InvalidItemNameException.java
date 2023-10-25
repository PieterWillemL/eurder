package com.switchfully.eurder.exceptions;

public class InvalidItemNameException extends RuntimeException{
    public InvalidItemNameException(){
        super("Provided Item name cannot be null or empty");
    }
}
