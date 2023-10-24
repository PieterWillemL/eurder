package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.Customer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String orderId;
    private final Customer customer;
    private final List<ItemGroup> itemGroupList;
    private double totalPrice;

    public Order(Customer customer, List<ItemGroup> itemGroupList) {
        orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.itemGroupList = itemGroupList;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(customer, order.customer) && Objects.equals(itemGroupList, order.itemGroupList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customer, itemGroupList);
    }
}
