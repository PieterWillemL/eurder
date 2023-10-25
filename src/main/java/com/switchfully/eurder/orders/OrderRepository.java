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

    public List<Order> getAllOrdersThatContainAnItemToShipInNumberOfDays(Integer numberOfDays) {
        return orders.values().stream()
                .filter(order -> orderContainsItemToShipInNumberOfDays(order, numberOfDays))
                .toList();
    }

    private boolean orderContainsItemToShipInNumberOfDays(Order order, Integer numberOfDays) {
        return order.getItemGroupList().stream()
                .map(ItemGroup::getShippingDate)
                .anyMatch(shippingDate -> shippingDate.equals(LocalDate.now().plusDays(numberOfDays)));
    }
}
