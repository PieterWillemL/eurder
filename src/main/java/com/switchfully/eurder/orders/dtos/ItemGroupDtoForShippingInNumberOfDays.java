package com.switchfully.eurder.orders.dtos;

import java.util.Objects;

public class ItemGroupDtoForShippingInNumberOfDays {

    private String name;
    private Integer amount;

    public ItemGroupDtoForShippingInNumberOfDays(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroupDtoForShippingInNumberOfDays that = (ItemGroupDtoForShippingInNumberOfDays) o;
        return Objects.equals(name, that.name) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }
}
