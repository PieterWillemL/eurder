package com.switchfully.eurder.order.dtos;

import com.switchfully.eurder.customers.Customer;

import java.util.List;
import java.util.Objects;

public class OrderDto {

    private String id;
    private Customer customer;
    private List<ItemGroupDto> itemGroupDtoList;

    public String getId() {
        return id;
    }

    public OrderDto setId(String id) {
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderDto setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<ItemGroupDto> getItemGroupDtoList() {
        return itemGroupDtoList;
    }

    public OrderDto setItemGroupDtoList(List<ItemGroupDto> itemGroupDtoList) {
        this.itemGroupDtoList = itemGroupDtoList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(customer, orderDto.customer) && Objects.equals(itemGroupDtoList, orderDto.itemGroupDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, itemGroupDtoList);
    }
}
