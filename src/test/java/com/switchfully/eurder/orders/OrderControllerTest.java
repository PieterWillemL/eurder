package com.switchfully.eurder.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.eurder.customers.Customer;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.security.SecurityService;
import com.switchfully.eurder.security.UsernamePassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String authorization = "authorization";
    private NewItemGroupDto newItemGroupDto1;
    private NewItemGroupDto newItemGroupDto2;

    private String newItemGroupDtoListString;
    private OrderDto orderDto;
    private String orderDtoString;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @MockBean
    private SecurityService securityService;

    @BeforeEach
    void setup() throws Exception{
        newItemGroupDto1 = new NewItemGroupDto()
                .setItemName("Wooden Desk")
                .setAmount(1);
        newItemGroupDto2 = new NewItemGroupDto()
                .setItemName("Coffee Mug")
                .setAmount(25);
        newItemGroupDtoListString = objectMapper.writeValueAsString(List.of(newItemGroupDto1, newItemGroupDto2));
        orderDto = new OrderDto()
                .setId("mockId")
                .setCustomer(new Customer("a", "b", "c", "d", "e", "password"))
                .setTotalPrice(120.5);
        orderDtoString = objectMapper.writeValueAsString(orderDto);

    }

    @Test
    void placeNewOrder_givenValidInput_thenReturnsHttpStatusCreatedAndCorrectReturnType() throws Exception{
        Mockito.when(orderService.placeNewOrder(List.of(newItemGroupDto1, newItemGroupDto2), "mockCustomerEmail"))
                .thenReturn(orderDto);
        Mockito.when(securityService.getUsernamePassword(""))
                        .thenReturn(new UsernamePassword("mockCustomerEmail", "password"));

        mockMvc.perform(post("http://localhost:8080/eurder/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header(authorization, "")
                .content(newItemGroupDtoListString))
                .andExpect(status().isCreated())
                .andExpect(content().json(orderDtoString));
    }
}
