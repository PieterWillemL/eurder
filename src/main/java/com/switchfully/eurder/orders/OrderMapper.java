package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.orders.dtos.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderMapper {
    private static String getAddress(CustomerService customerService, Order order) {
        return customerService.getCustomerByEmail(order.getCustomerEmail()).getAddress();
    }

    public Order mapToOrder(List<ItemGroup> itemGroupList, String customerEmail) {
        return new Order(customerEmail, itemGroupList);
    }

    public ItemGroup mapToItemGroupBeforeCalculatingShippingDateAndSettingUnitPrice(NewItemGroupDto newItemGroupDto) {
        return new ItemGroup(
                newItemGroupDto.getItemName(),
                newItemGroupDto.getAmount(),
                0, null);
    }

    public OrderDto mapToOrderDto(Order order) {
        List<ItemGroupDto> itemGroupDtoList = order.getItemGroupList()
                .stream()
                .map(this::mapToItemGroupDto)
                .toList();
        return new OrderDto(order.getOrderId(), order.getCustomerEmail(), itemGroupDtoList, order.getTotalPrice());

    }

    public ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto(itemGroup.getItemName(), itemGroup.getAmount(), itemGroup.getPricePerUnit(), itemGroup.getShippingDate());
    }

    public OrderReportDto mapToOrderReportDto(List<Order> orders, Double totalPrice) {
        List<SingleOrderForReportDto> singleOrderForReportDtoList = orders.stream()
                .map(this::mapToSingleOrderForReportDto)
                .toList();
        return new OrderReportDto(singleOrderForReportDtoList, totalPrice);
    }

    public SingleOrderForReportDto mapToSingleOrderForReportDto(Order order) {
        List<ItemGroupForOrderReportDto> itemGroupForOrderReportDtoList = order.getItemGroupList().stream()
                .map(this::mapToItemGroupForOrderReportDto)
                .toList();
        Double totalPrice = itemGroupForOrderReportDtoList.stream()
                .mapToDouble(ItemGroupForOrderReportDto::getTotalPriceForItemGroup)
                .sum();
        return new SingleOrderForReportDto(order.getOrderId(), itemGroupForOrderReportDtoList, totalPrice);
    }

    public ItemGroupForOrderReportDto mapToItemGroupForOrderReportDto(ItemGroup itemGroup) {
        return new ItemGroupForOrderReportDto(itemGroup.getItemName(), itemGroup.getAmount(), itemGroup.getPricePerUnit() * itemGroup.getAmount());
    }

    public NewItemGroupDto mapToNewItemGroupDto(ItemGroup itemGroup) {
        return new NewItemGroupDto(itemGroup.getItemName(), itemGroup.getAmount());
    }

    public ShippingInNumberOfDaysDto mapToShippingInNumberOfDaysDto(List<Order> ordersWithItemToShipInNumberOfDays, CustomerService customerService, Integer numberOfDays) {
        HashMap<String, List<ItemGroupDtoForShippingInNumberOfDays>> itemsByAddress = new HashMap<>();
        ordersWithItemToShipInNumberOfDays
                .stream().map(order -> itemsByAddress.containsKey(getAddress(customerService, order)) ?
                        itemsByAddress.put(getAddress(customerService, order), appendList(itemsByAddress, customerService, order, numberOfDays)) :
                        itemsByAddress.put(getAddress(customerService, order), getItemGroupDtoForShippingTodayList(order, numberOfDays))
                );

        return new ShippingInNumberOfDaysDto(itemsByAddress);
    }

    private List<ItemGroupDtoForShippingInNumberOfDays> appendList(HashMap<String, List<ItemGroupDtoForShippingInNumberOfDays>> itemsByAddress, CustomerService customerService, Order order, Integer numberOfDays) {
        List<ItemGroupDtoForShippingInNumberOfDays> listToReturn = itemsByAddress.get(getAddress(customerService, order));
        listToReturn.addAll(getItemGroupDtoForShippingTodayList(order, numberOfDays));
        return listToReturn;
    }

    private List<ItemGroupDtoForShippingInNumberOfDays> getItemGroupDtoForShippingTodayList(Order order, Integer numberOfDays) {
        return order.getItemGroupList().stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now().plusDays(numberOfDays)))
                .map(this::mapToItemGroupDtoForShippingInNumberOfDays)
                .toList();
    }

    public ItemGroupDtoForShippingInNumberOfDays mapToItemGroupDtoForShippingInNumberOfDays(ItemGroup itemGroup) {
        return new ItemGroupDtoForShippingInNumberOfDays(itemGroup.getItemName(), itemGroup.getAmount());
    }
}
