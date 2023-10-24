package com.switchfully.eurder.order.dtos;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroupDto {
    private String itemName;
    private int amount;
    private double pricePerUnit;
    private LocalDate shippingDate;

    public String getItemName() {
        return itemName;
    }

    public ItemGroupDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public ItemGroupDto setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public ItemGroupDto setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroupDto that = (ItemGroupDto) o;
        return amount == that.amount && Double.compare(pricePerUnit, that.pricePerUnit) == 0 && Objects.equals(itemName, that.itemName) && Objects.equals(shippingDate, that.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, amount, pricePerUnit, shippingDate);
    }
}
