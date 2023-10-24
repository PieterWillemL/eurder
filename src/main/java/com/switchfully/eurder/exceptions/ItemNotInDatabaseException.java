package com.switchfully.eurder.exceptions;

public class ItemNotInDatabaseException extends RuntimeException{

    public ItemNotInDatabaseException(String itemName){
        super("The following item is not present in our database: " + itemName);
    }
}
