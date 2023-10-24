package com.switchfully.eurder.order;

import com.switchfully.eurder.order.dtos.ItemGroupDto;
import com.switchfully.eurder.order.dtos.NewItemGroupDto;
import com.switchfully.eurder.order.dtos.OrderDto;
import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("eurder/orders")
public class OrderController {

    private final OrderService orderService;

    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeNewOrder(@RequestHeader String authorization, @RequestBody List<NewItemGroupDto> newItemGroupDtoList){
        securityService.validateAuthorization(authorization, Role.CUSTOMER);
        String customerEmail = securityService.getUsernamePassword(authorization).getUsername();
        return orderService.placeNewOrder(newItemGroupDtoList, customerEmail);
    }
}
