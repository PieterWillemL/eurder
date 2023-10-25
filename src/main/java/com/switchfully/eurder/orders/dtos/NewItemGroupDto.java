package com.switchfully.eurder.orders.dtos;

import java.util.Objects;

public class NewItemGroupDto {

    private final String itemName;
    private final int amount;

    public NewItemGroupDto(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }


    public int getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewItemGroupDto that = (NewItemGroupDto) o;
        return amount == that.amount && Objects.equals(itemName, that.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, amount);
    }
}
