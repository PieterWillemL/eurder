package com.switchfully.eurder.orders.dtos;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ShippingTodayDto {
    private final HashMap<String, List<ItemGroupDtoForShippingToday>> itemsByAddress;

    public ShippingTodayDto(HashMap<String, List<ItemGroupDtoForShippingToday>> itemsByAddress) {
        this.itemsByAddress = itemsByAddress;
    }

    public HashMap<String, List<ItemGroupDtoForShippingToday>> getItemsByAddress() {
        return itemsByAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingTodayDto that = (ShippingTodayDto) o;
        return Objects.equals(itemsByAddress, that.itemsByAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemsByAddress);
    }
}
