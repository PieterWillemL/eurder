package com.switchfully.eurder.exceptions;

import com.switchfully.eurder.items.Item;

public class ItemAlreadyInDatabaseException extends RuntimeException{

    public ItemAlreadyInDatabaseException(Item item){
        super("Our database already contains following item:\n" + item.toString());
    }
}
