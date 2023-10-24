package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.Customer;
import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    public static final int DAYS_UNTIL_SHIPPING_WHEN_IN_STOCK = 1;
    public static final int DAYS_UNTIL_SHIPPING_WHEN_NOT_IN_STOCK = 7;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerService customerService, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    public OrderDto placeNewOrder(List<NewItemGroupDto> newItemGroupDtoList, String customerEmail) {
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        List<ItemGroup> itemGroupList = newItemGroupDtoList.stream()
                .map(orderMapper::mapToItemGroupBeforeCalculatingShippingDateAndSettingUnitPrice)
                .map(this::calculateShippingDate)
                .map(this::setCurrentUnitPrice)
                .toList();
        Order placedOrder = orderRepository.placeNewOrder(orderMapper.mapToOrder(itemGroupList, customer));
        placedOrder.setTotalPrice(calculateTotalPrice(placedOrder));
        return orderMapper.mapToOrderDto(placedOrder);
    }

    private double calculateTotalPrice(Order order) {
        return order.getItemGroupList().stream()
                .mapToDouble(itemGroup -> itemGroup.getPricePerUnit() * itemGroup.getAmount())
                .sum();
    }

    private ItemGroup calculateShippingDate(ItemGroup itemGroup) {
        Item item = itemService.getItemByItemName(itemGroup.getItemName());
        if (itemGroup.getAmount() <= item.getAmount()) {
            itemGroup.setShippingDate(LocalDate.now().plusDays(DAYS_UNTIL_SHIPPING_WHEN_IN_STOCK));
        } else {
            itemGroup.setShippingDate(LocalDate.now().plusDays(DAYS_UNTIL_SHIPPING_WHEN_NOT_IN_STOCK));
        }
        return itemGroup;
    }

    private ItemGroup setCurrentUnitPrice(ItemGroup itemGroup) {
        Item item = itemService.getItemByItemName(itemGroup.getItemName());
        itemGroup.setPricePerUnit(item.getPrice());
        return itemGroup;
    }
}
