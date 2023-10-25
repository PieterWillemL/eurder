package com.switchfully.eurder.items.dtos;

import java.util.Objects;

public class ItemDto {

    private final String name;
    private final Double price;
    private final Integer amount;
    private final String description;

    public ItemDto(String name, Double price, Integer amount, String description) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAmount() {
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
        return Objects.equals(name, itemDto.name) && Objects.equals(price, itemDto.price) && Objects.equals(amount, itemDto.amount) && Objects.equals(description, itemDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount, description);
    }
}

