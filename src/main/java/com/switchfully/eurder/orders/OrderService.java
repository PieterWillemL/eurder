package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.exceptions.OrderNotOfThisCustomerException;
import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.dtos.OrderReportDto;
import com.switchfully.eurder.orders.dtos.ShippingTodayDto;
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
        customerService.getCustomerByEmail(customerEmail);
        List<ItemGroup> itemGroupList = newItemGroupDtoList.stream()
                .map(orderMapper::mapToItemGroupBeforeCalculatingShippingDateAndSettingUnitPrice)
                .map(this::calculateShippingDate)
                .map(this::setCurrentUnitPrice)
                .toList();
        Order placedOrder = orderRepository.placeNewOrder(orderMapper.mapToOrder(itemGroupList, customerEmail));
        itemService.updateAmounts(orderMapper.mapToOrder(itemGroupList, customerEmail));
        placedOrder.setTotalPrice(calculateTotalPriceOfOneOrder(placedOrder));
        return orderMapper.mapToOrderDto(placedOrder);
    }

    private double calculateTotalPriceOfOneOrder(Order order) {
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

    public OrderReportDto getOrderReport(String customerEmail) {
        List<Order> customerOrders = orderRepository.getOrdersByCustomerEmail(customerEmail);
        Double totalPrice = customerOrders.stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
        return orderMapper.mapToOrderReportDto(customerOrders, totalPrice);
    }

    public OrderDto reorderExistingOrder(String customerEmail, String orderId) {
        Order orderToReorder = orderRepository.getOrderById(orderId);
        validateOrderByThisCustomer(customerEmail, orderId, orderToReorder);
        List<NewItemGroupDto> newItemGroupDtoList = orderToReorder.getItemGroupList().stream()
                .map(orderMapper::mapToNewItemGroupDto)
                .toList();

        return placeNewOrder(newItemGroupDtoList, customerEmail);
    }

    private static void validateOrderByThisCustomer(String customerEmail, String orderId, Order orderToReorder) {
        if(!orderToReorder.getCustomerEmail().equals(customerEmail)){
            throw new OrderNotOfThisCustomerException(orderId, customerEmail);
        }
    }

    public ShippingTodayDto getItemsShippingToday() {
        List<Order> ordersWithItemToShipToday = orderRepository.getAllOrdersThatContainAnItemToShipToday();
        return orderMapper.mapToShippingTodayDto(ordersWithItemToShipToday, customerService);
    }
}
