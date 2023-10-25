package com.switchfully.eurder.orders;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    public List<Order> getAllOrdersThatContainAnItemToShipToday() {
        return orders.values().stream()
                .filter(this::orderContainsItemToShipToday)
                .toList();
    }

    private boolean orderContainsItemToShipToday(Order order) {
        return order.getItemGroupList().stream()
                .map(ItemGroup::getShippingDate)
                .anyMatch(shippingDate -> shippingDate.equals(LocalDate.now()));

    }
}
