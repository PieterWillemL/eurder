package com.switchfully.eurder.items.dtos;

import java.util.Objects;

public class ItemDto {

    private String name;
    private double price;
    private int amount;
    private String description;

    public String getName() {
        return name;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDto setDescription(String description) {
        this.description = description;
        return this;
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
