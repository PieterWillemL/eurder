package com.switchfully.eurder.orders;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private OrderService orderService;

    private final OrderMapper orderMapper = new OrderMapper();

    @Mock
    private OrderRepository orderRepository;


}
