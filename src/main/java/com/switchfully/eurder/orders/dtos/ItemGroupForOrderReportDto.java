package com.switchfully.eurder.orders.dtos;

import java.util.Objects;

public class ItemGroupForOrderReportDto {

    private final String name;
    private final Integer amount;
    private final Double totalPriceForItemGroup;

    public ItemGroupForOrderReportDto(String name, Integer amount, Double totalPriceForItemGroup) {
        this.name = name;
        this.amount = amount;
        this.totalPriceForItemGroup = totalPriceForItemGroup;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getTotalPriceForItemGroup() {
        return totalPriceForItemGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroupForOrderReportDto that = (ItemGroupForOrderReportDto) o;
        return Objects.equals(name, that.name) && Objects.equals(amount, that.amount) && Objects.equals(totalPriceForItemGroup, that.totalPriceForItemGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, totalPriceForItemGroup);
    }

    @Override
    public String toString() {
        return "ItemGroupForOrderReportDto{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", totalPriceForItemGroup=" + totalPriceForItemGroup +
                '}';
    }
}
