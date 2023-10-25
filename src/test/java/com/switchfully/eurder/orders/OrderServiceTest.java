package com.switchfully.eurder.orders;

import com.switchfully.eurder.customers.CustomerService;
import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.ItemGroupDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
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


    @BeforeEach
    void setup() {
        orderService = new OrderService(orderRepository, orderMapper, customerService, itemService);
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
            Mockito.when(orderRepository.placeNewOrder(Mockito.any(Order.class)))
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
            Mockito.when(orderRepository.placeNewOrder(Mockito.any(Order.class)))
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
            Mockito.when(orderRepository.placeNewOrder(Mockito.any(Order.class)))
                    .thenReturn(orderToPlace);
            NewItemGroupDto newItemGroupDto1WithHigherAmount = new NewItemGroupDto("Wooden Desk", 1000);

            OrderDto actualReturnValue = orderService.placeNewOrder(List.of(newItemGroupDto1WithHigherAmount), "mockEmail");

            Assertions.assertThat(actualReturnValue.getItemGroupDtoList().stream()
                            .map(ItemGroupDto::getShippingDate)
                            .findFirst())
                    .isEqualTo(Optional.of(LocalDate.now().plusDays(7)));

        }
    }
}
