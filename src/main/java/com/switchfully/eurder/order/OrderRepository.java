package com.switchfully.eurder.order;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {

    private final HashMap<String, Order> orders = new HashMap<>();
    public Order placeNewOrder(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }
}
