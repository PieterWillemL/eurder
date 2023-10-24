package com.switchfully.eurder.order;

import com.switchfully.eurder.customers.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private String orderId;
    private Customer customer;
    private List<ItemGroup> itemGroupList;

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
