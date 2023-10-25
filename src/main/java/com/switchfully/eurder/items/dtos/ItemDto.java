package com.switchfully.eurder.items.dtos;

import java.util.Objects;

public class ItemDto {

    private final String name;
    private final double price;
    private final int amount;
    private final String description;

    public ItemDto(String name, double price, int amount, String description) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Double.compare(price, itemDto.price) == 0 && amount == itemDto.amount && Objects.equals(name, itemDto.name) && Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount, description);
    }
}
