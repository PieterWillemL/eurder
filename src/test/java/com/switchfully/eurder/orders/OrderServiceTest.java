package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.exceptions.OrderNotOfThisCustomerException;
import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private final OrderMapper orderMapper = new OrderMapper();
    private OrderService orderService;
    private NewItemGroupDto newItemGroupDto1;
    private NewItemGroupDto newItemGroupDto2;
    private Item item1;
    private Item item2;
    private Order orderToPlace;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ItemService itemService;
    @Mock
    private CustomerService customerService;

    private static OrderReportDto getOrderReportDto(Order order1, Order order2) {
        ItemGroupForOrderReportDto itemGroupForOrderReportDto1 = new ItemGroupForOrderReportDto("item", 2, 5.00);
        ItemGroupForOrderReportDto itemGroupForOrderReportDto2 = new ItemGroupForOrderReportDto("item2", 1, 3.40);
        ItemGroupForOrderReportDto itemGroupForOrderReportDto3 = new ItemGroupForOrderReportDto("item3", 4, 4.40);
        SingleOrderForReportDto singleOrderForReportDto1 = new SingleOrderForReportDto(
                order1.getOrderId(),
                List.of(itemGroupForOrderReportDto1, itemGroupForOrderReportDto2),
                8.40);
        SingleOrderForReportDto singleOrderForReportDto2 = new SingleOrderForReportDto(
                order2.getOrderId(),
                List.of(itemGroupForOrderReportDto3),
                4.40);
        return new OrderReportDto(List.of(singleOrderForReportDto1, singleOrderForReportDto2), 12.80);
    }

    @BeforeEach
    void setup() {
        orderService = new OrderService(orderRepository, orderMapper, customerService, itemService);
    }

    @Test
    void getOrderReport_givenValidCustomerEmail_thenReturnsOrderReportDtoWithCorrectTotalPrice() {
        List<ItemGroup> itemGroupList1 = List.of(
                new ItemGroup("item", 2, 2.50, LocalDate.now()),
                new ItemGroup("item2", 1, 3.40, LocalDate.now()));
        List<ItemGroup> itemGroupList2 = List.of(
                new ItemGroup("item3", 4, 1.10, LocalDate.now()));
        Order order1 = new Order("mockCustomerEmail", itemGroupList1);
        order1.setTotalPrice(8.40);
        Order order2 = new Order("mockCustomerEmail", itemGroupList2);
        order2.setTotalPrice(4.40);
        List<Order> mockCustomerOrders = List.of(order1, order2);

        Mockito.when(orderRepository.getOrdersByCustomerEmail("mockCustomerEmail"))
                .thenReturn(mockCustomerOrders);

        OrderReportDto expectedReturnValue = getOrderReportDto(order1, order2);

        OrderReportDto actualReturnValue = orderService.getOrderReport("mockCustomerEmail");

        Assertions.assertThat(actualReturnValue).isEqualTo(expectedReturnValue);
    }

    @Nested
    @DisplayName("Place new order")
    class PlaceNewOrder {
        @BeforeEach
        void setup() {
            newItemGroupDto1 = new NewItemGroupDto("Wooden Desk", 1);
            newItemGroupDto2 = new NewItemGroupDto("Coffee Mug", 10);
            item1 = new Item("Wooden Desk", 250.55, 5, "description");
            item2 = new Item("Coffee Mug", 3.10, 150, "description");
            Mockito.when(itemService.getItemByItemName("Wooden Desk"))
                    .thenReturn(item1);
        }

        @Test
        void givenListOfItems_thenReturnsOrderWithCorrectTotalPrice() {
            orderToPlace = new Order(
                    "mockEmail",
                    List.of(
                            new ItemGroup("Wooden Desk", 1, 250.55, LocalDate.now().plusDays(1)),
                            new ItemGroup("Coffee Mug", 10, 3.10, LocalDate.now().plusDays(1)))
            );
            Mockito.when(itemService.getItemByItemName("Coffee Mug"))
                    .thenReturn(item2);
            Mockito.when(orderRepository.placeNewOrder(any(Order.class)))
                    .thenReturn(orderToPlace);

            OrderDto actualReturnValue = orderService.placeNewOrder(List.of(newItemGroupDto1, newItemGroupDto2), "mockEmail");
            OrderDto expectedReturnValue = new OrderDto(
                    actualReturnValue.getId(),
                    "mockEmail",
                    List.of(new ItemGroupDto("Wooden Desk", 1, 250.55, LocalDate.now().plusDays(1)),
                            new ItemGroupDto("Coffee Mug", 10, 3.10, LocalDate.now().plusDays(1))),
                    281.55);

            Assertions.assertThat(actualReturnValue).isEqualTo(expectedReturnValue);
        }

        @Test
        void givenItemWithEnoughStock_thenItemGroupGetsCorrectShippingDate() {
            orderToPlace = new Order(
                    "mockEmail",
                    List.of(
                            new ItemGroup("Wooden Desk", 1, 250.55, LocalDate.now().plusDays(1)),
                            new ItemGroup("Coffee Mug", 10, 3.10, LocalDate.now().plusDays(1)))
            );
            Mockito.when(itemService.getItemByItemName("Coffee Mug"))
                    .thenReturn(item2);
            Mockito.when(orderRepository.placeNewOrder(any(Order.class)))
                    .thenReturn(orderToPlace);
            OrderDto actualReturnValue = orderService.placeNewOrder(List.of(newItemGroupDto1, newItemGroupDto2), "mockEmail");

            Assertions.assertThat(actualReturnValue.getItemGroupDtoList().stream()
                            .map(ItemGroupDto::getShippingDate)
                            .findFirst())
                    .isEqualTo(Optional.of(LocalDate.now().plusDays(1)));
        }

        @Test
        void givenItemWithNotEnoughStock_thenItemGroupGetsCorrectShippingDate() {
            orderToPlace = new Order(
                    "mockEmail",
                    List.of(new ItemGroup("Wooden Desk", 1000, 250.55, LocalDate.now().plusDays(7)))
            );
            Mockito.when(orderRepository.placeNewOrder(any(Order.class)))
                    .thenReturn(orderToPlace);
            NewItemGroupDto newItemGroupDto1WithHigherAmount = new NewItemGroupDto("Wooden Desk", 1000);

            OrderDto actualReturnValue = orderService.placeNewOrder(List.of(newItemGroupDto1WithHigherAmount), "mockEmail");

            Assertions.assertThat(actualReturnValue.getItemGroupDtoList().stream()
                            .map(ItemGroupDto::getShippingDate)
                            .findFirst())
                    .isEqualTo(Optional.of(LocalDate.now().plusDays(7)));

        }
    }

    @Nested
    @DisplayName("Reorder existing Order")
    class ReorderExistingOrder {
        @BeforeEach
        void setup(){
            newItemGroupDto1 = new NewItemGroupDto("Wooden Desk", 1);
            item1 = new Item("Wooden Desk", 250.55, 5, "description");
            orderToPlace = new Order(
                    "mockEmail",
                    List.of(new ItemGroup("Wooden Desk", 1, 250.55, LocalDate.now().plusDays(1))));
            Mockito.when(orderRepository.getOrderById("mockId"))
                    .thenReturn(orderToPlace);
        }
        @Test
        void givenValidInput_thenReturnsCorrectValue() {
            Mockito.when(itemService.getItemByItemName("Wooden Desk"))
                    .thenReturn(item1);

            Mockito.when(orderRepository.placeNewOrder(any(Order.class)))
                    .thenReturn(orderToPlace);

            OrderDto actualReturnValue = orderService.reorderExistingOrder("mockEmail", "mockId");
            OrderDto expectedReturnValue = new OrderDto(actualReturnValue.getId(), "mockEmail", List.of(new ItemGroupDto("Wooden Desk", 1, 250.55, LocalDate.now().plusDays(1))), 250.55);

            Assertions.assertThat(actualReturnValue).isEqualTo(expectedReturnValue);
        }

        @Test
        void givenCustomerEmailNotSameAsOrderCustomerEmail_thenThrowsOrderNotOfThisCustomerException() throws Exception{
            Assertions.assertThatThrownBy(()->orderService.reorderExistingOrder("otherMockEmail", "mockId"))
                    .isInstanceOf(OrderNotOfThisCustomerException.class);
        }
    }
}
