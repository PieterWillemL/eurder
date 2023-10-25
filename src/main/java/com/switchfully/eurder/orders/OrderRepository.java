package com.switchfully.eurder.orders;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private final HashMap<String, Order> orders = new HashMap<>();
    public Order placeNewOrder(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public List<Order> getOrdersByCustomerEmail(String customerEmail) {
        return orders.values().stream()
                .filter(order -> order.getCustomerEmail().equals(customerEmail))
                .toList();
    }

    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }
}
