package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.Customer;
import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.orders.dtos.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class OrderMapper {
    public Order mapToOrder(List<ItemGroup> itemGroupList, String customerEmail)  {
        return new Order(customerEmail, itemGroupList);
    }

    public ItemGroup mapToItemGroupBeforeCalculatingShippingDateAndSettingUnitPrice(NewItemGroupDto newItemGroupDto) {
        return new ItemGroup(
                newItemGroupDto.getItemName(),
                newItemGroupDto.getAmount(),
                0,null);
    }

    public OrderDto mapToOrderDto(Order order){
        List<ItemGroupDto> itemGroupDtoList= order.getItemGroupList()
                .stream()
                .map(this::mapToItemGroupDto)
                .toList();
        return new OrderDto(order.getOrderId(), order.getCustomerEmail(), itemGroupDtoList, order.getTotalPrice());

    }

    public ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup){
        return new ItemGroupDto(itemGroup.getItemName(), itemGroup.getAmount(), itemGroup.getPricePerUnit(), itemGroup.getShippingDate());
    }

    public OrderReportDto mapToOrderReportDto(List<Order> orders, Double totalPrice){
        List<SingleOrderForReportDto> singleOrderForReportDtoList = orders.stream()
                .map(this::mapToSingleOrderForReportDto)
                .toList();
        return new OrderReportDto(singleOrderForReportDtoList, totalPrice);
    }

    public SingleOrderForReportDto mapToSingleOrderForReportDto(Order order){
        List<ItemGroupForOrderReportDto> itemGroupForOrderReportDtoList = order.getItemGroupList().stream()
                .map(this::mapToItemGroupForOrderReportDto)
                .toList();
        Double totalPrice = itemGroupForOrderReportDtoList.stream()
                .mapToDouble(ItemGroupForOrderReportDto::getTotalPriceForItemGroup)
                .sum();
        return new SingleOrderForReportDto(order.getOrderId(), itemGroupForOrderReportDtoList, totalPrice);
    }

    public ItemGroupForOrderReportDto mapToItemGroupForOrderReportDto(ItemGroup itemGroup){
        return new ItemGroupForOrderReportDto(itemGroup.getItemName(), itemGroup.getAmount(), itemGroup.getPricePerUnit()* itemGroup.getAmount());
    }

    public NewItemGroupDto mapToNewItemGroupDto(ItemGroup itemGroup) {
        return new NewItemGroupDto(itemGroup.getItemName(), itemGroup.getAmount());
    }

    public ShippingTodayDto mapToShippingTodayDto(List<Order> ordersWithItemToShipToday, CustomerService customerService) {
        HashMap<String, List<ItemGroupDtoForShippingToday>> itemsByAddress = new HashMap<>();
        ordersWithItemToShipToday.stream()
                .map(order -> itemsByAddress.containsKey(getAddress(customerService, order))?
                        itemsByAddress.put(getAddress(customerService, order), appendList(itemsByAddress, customerService, order)):
                        itemsByAddress.put(getAddress(customerService, order), getItemGroupDtoForShippingTodayList(order))
                        );
        return new ShippingTodayDto(itemsByAddress);
    }

    private List<ItemGroupDtoForShippingToday> appendList(HashMap<String, List<ItemGroupDtoForShippingToday>> itemsByAddress, CustomerService customerService, Order order){
        List<ItemGroupDtoForShippingToday> listToReturn = itemsByAddress.get(getAddress(customerService, order));
        listToReturn.addAll(getItemGroupDtoForShippingTodayList(order));
        return listToReturn;
    }

    private static String getAddress(CustomerService customerService, Order order) {
        return customerService.getCustomerByEmail(order.getCustomerEmail()).getAddress();
    }

    private List<ItemGroupDtoForShippingToday> getItemGroupDtoForShippingTodayList(Order order){
        return order.getItemGroupList().stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .map(this::mapToItemGroupDtoForShippingToday)
                .toList();
    }

    public ItemGroupDtoForShippingToday mapToItemGroupDtoForShippingToday(ItemGroup itemGroup){
        return new ItemGroupDtoForShippingToday(itemGroup.getItemName(), itemGroup.getAmount());
    }
}
