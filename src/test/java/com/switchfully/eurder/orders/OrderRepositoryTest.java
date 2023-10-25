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
}
