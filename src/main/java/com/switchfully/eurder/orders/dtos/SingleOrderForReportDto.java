package com.switchfully.eurder.orders.dtos;

import java.util.List;
import java.util.Objects;

public class SingleOrderForReportDto {

    private final String id;
    private final List<ItemGroupForOrderReportDto> itemGroupForOrderReportDtoList;
    private final Double totalPriceOrder;

    public SingleOrderForReportDto(String id, List<ItemGroupForOrderReportDto> itemGroupForOrderReportDtoList, Double totalPriceOrder) {
        this.id = id;
        this.itemGroupForOrderReportDtoList = itemGroupForOrderReportDtoList;
        this.totalPriceOrder = totalPriceOrder;
    }

    public String getId() {
        return id;
    }

    public List<ItemGroupForOrderReportDto> getItemGroupForOrderReportDtoList() {
        return itemGroupForOrderReportDtoList;
    }

    public Double getTotalPriceOrder() {
        return totalPriceOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleOrderForReportDto that = (SingleOrderForReportDto) o;
        return Objects.equals(id, that.id) && Objects.equals(itemGroupForOrderReportDtoList, that.itemGroupForOrderReportDtoList) && Objects.equals(totalPriceOrder, that.totalPriceOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemGroupForOrderReportDtoList, totalPriceOrder);
    }

    @Override
    public String toString() {
        return "SingleOrderForReportDto{" +
                "id='" + id + '\'' +
                ", itemGroupForOrderReportDtoList=" + itemGroupForOrderReportDtoList +
                ", totalPriceOrder=" + totalPriceOrder +
                '}';
    }
}
