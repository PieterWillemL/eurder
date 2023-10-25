package com.switchfully.eurder.orders.dtos;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroupDto {
    private final String itemName;
    private final int amount;
    private final double pricePerUnit;
    private final LocalDate shippingDate;

    public ItemGroupDto(String itemName, int amount, double pricePerUnit, LocalDate shippingDate) {
        this.itemName = itemName;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.shippingDate = shippingDate;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
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
