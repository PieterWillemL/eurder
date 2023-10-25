package com.switchfully.eurder.exceptions;

public class OrderNotOfThisCustomerException extends RuntimeException{

    public OrderNotOfThisCustomerException(String orderId, String customerEmail){
        super("Order with id: " + orderId + " does not belong to " + customerEmail);
    }
}
