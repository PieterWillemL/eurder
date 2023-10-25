package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.Customer;
import com.switchfully.eurder.orders.dtos.ItemGroupDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
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
        return new OrderDto(order.getOrderId(), order.getCustomer(), itemGroupDtoList, order.getTotalPrice());

    }

    public ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup){
        return new ItemGroupDto(itemGroup.getItemName(), itemGroup.getAmount(), itemGroup.getPricePerUnit(), itemGroup.getShippingDate());
    }
}
