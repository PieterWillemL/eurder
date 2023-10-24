package com.switchfully.eurder.order.dtos;

import com.switchfully.eurder.items.Item;

import java.util.Objects;

public class NewItemGroupDto {

    private String itemName;
    private int amount;

    public String getItemName() {
        return itemName;
    }

    public NewItemGroupDto setItem(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public NewItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
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
