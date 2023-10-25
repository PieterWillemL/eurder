package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.customers.Customer;

import java.util.List;
import java.util.Objects;

public class OrderDto {

    private final String id;
    private final String customerEmail;
    private final List<ItemGroupDto> itemGroupDtoList;
    private final double totalPrice;

    public OrderDto(String id, String customerEmail, List<ItemGroupDto> itemGroupDtoList, double totalPrice) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.itemGroupDtoList = itemGroupDtoList;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<ItemGroupDto> getItemGroupDtoList() {
        return itemGroupDtoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Double.compare(totalPrice, orderDto.totalPrice) == 0 && Objects.equals(id, orderDto.id) && Objects.equals(customerEmail, orderDto.customerEmail) && Objects.equals(itemGroupDtoList, orderDto.itemGroupDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerEmail, itemGroupDtoList, totalPrice);
    }
}
