package com.switchfully.eurder.orders.dtos;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ShippingInNumberOfDaysDto {
    private final HashMap<String, List<ItemGroupDtoForShippingInNumberOfDays>> itemsByAddress;

    public ShippingInNumberOfDaysDto(HashMap<String, List<ItemGroupDtoForShippingInNumberOfDays>> itemsByAddress) {
        this.itemsByAddress = itemsByAddress;
    }

    public HashMap<String, List<ItemGroupDtoForShippingInNumberOfDays>> getItemsByAddress() {
        return itemsByAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingInNumberOfDaysDto that = (ShippingInNumberOfDaysDto) o;
        return Objects.equals(itemsByAddress, that.itemsByAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemsByAddress);
    }
}
