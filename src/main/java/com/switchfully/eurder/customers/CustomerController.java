package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import com.switchfully.eurder.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eurder/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final SecurityService securityService;

    public CustomerController(CustomerService customerService, SecurityService securityService){
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomerAccount(@RequestBody CreateCustomerDto createCustomerDto){
        return customerService.createCustomerAccount(createCustomerDto);
    }

}
