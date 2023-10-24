package com.switchfully.eurder.order;

import com.switchfully.eurder.customers.Customer;
import com.switchfully.eurder.order.dtos.ItemGroupDto;
import com.switchfully.eurder.order.dtos.NewItemGroupDto;
import com.switchfully.eurder.order.dtos.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public Order mapToOrder(List<ItemGroup> itemGroupList, Customer customer)  {
        return new Order(customer, itemGroupList);
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
        return new OrderDto()
                .setId(order.getOrderId())
                .setCustomer(order.getCustomer())
                .setItemGroupDtoList(itemGroupDtoList);

    }

    public ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup){
        return new ItemGroupDto()
                .setItemName(itemGroup.getItemName())
                .setPricePerUnit(itemGroup.getPricePerUnit())
                .setAmount(itemGroup.getAmount())
                .setShippingDate(itemGroup.getShippingDate());
    }
}
