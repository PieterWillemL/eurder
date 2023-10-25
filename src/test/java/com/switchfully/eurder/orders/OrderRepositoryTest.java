package com.switchfully.eurder.orders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class OrderRepositoryTest {

    private OrderRepository orderRepository;

    private Order orderToTest;

    @BeforeEach
    void setup(){
        orderRepository = new OrderRepository();
        orderToTest = new Order("mockEmail", List.of(new ItemGroup("mockItem", 1, 1.1, LocalDate.now())));
    }

    @Test
    void placeNewOrder_ReturnsTheSameOrderItWasGiven(){
        Assertions.assertThat(orderRepository.placeNewOrder(orderToTest))
                .isEqualTo(orderToTest);
    }

    @Test
    void getOrdersByCustomerEmail_givenValidCustomerEmail_thenReturnsListOfThisCustomersOrders(){
        orderRepository.placeNewOrder(orderToTest);
        orderRepository.placeNewOrder(new Order("otherMockEmail", List.of(new ItemGroup("mockItem2", 2, 3.5, LocalDate.now()))));

        Assertions.assertThat(orderRepository.getOrdersByCustomerEmail("mockEmail"))
                .isEqualTo(List.of(orderToTest));
    }
}
