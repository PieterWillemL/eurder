package com.switchfully.eurder.orders;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroup {
    private final String itemName;
    private final int amount;
    private double pricePerUnit;
    private LocalDate shippingDate;

    public ItemGroup(String itemName, int amount, double pricePerUnit, LocalDate shippingDate) {
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

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate){
        this.shippingDate = shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroup itemGroup = (ItemGroup) o;
        return amount == itemGroup.amount && Objects.equals(itemName, itemGroup.itemName) && Objects.equals(shippingDate, itemGroup.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, amount, shippingDate);
    }
}
