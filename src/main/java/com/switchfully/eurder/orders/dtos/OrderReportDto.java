package com.switchfully.eurder.orders.dtos;

import java.util.List;
import java.util.Objects;

public class OrderReportDto {
    private final List<SingleOrderForReportDto> singleOrderForReportDtoList;
    private final Double totalPrice;

    public OrderReportDto(List<SingleOrderForReportDto> singleOrderForReportDtoList, Double totalPrice) {
        this.singleOrderForReportDtoList = singleOrderForReportDtoList;
        this.totalPrice = totalPrice;
    }

    public List<SingleOrderForReportDto> getOrderDtoList() {
        return singleOrderForReportDtoList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderReportDto that = (OrderReportDto) o;
        return Objects.equals(singleOrderForReportDtoList, that.singleOrderForReportDtoList) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singleOrderForReportDtoList, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderReportDto{" +
                "singleOrderForReportDtoList=" + singleOrderForReportDtoList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
