package com.switchfully.eurder.orders;

import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.dtos.OrderReportDto;
import com.switchfully.eurder.orders.dtos.ShippingTodayDto;
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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public OrderReportDto getOrderReport(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Role.CUSTOMER);
        String customerEmail = securityService.getUsernamePassword(authorization).getUsername();
        return orderService.getOrderReport(customerEmail);
    }

    @PostMapping("reorder/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorderExistingOrder(@RequestHeader String authorization, @PathVariable String orderId){
        securityService.validateAuthorization(authorization, Role.CUSTOMER);
        String customerEmail = securityService.getUsernamePassword(authorization).getUsername();
        return orderService.reorderExistingOrder(customerEmail, orderId);
    }

    @GetMapping("shipping-today")
    @ResponseStatus(HttpStatus.OK)
    public ShippingTodayDto getItemsShippingToday(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Role.ADMIN);
        return orderService.getItemsShippingToday();
    }
}
