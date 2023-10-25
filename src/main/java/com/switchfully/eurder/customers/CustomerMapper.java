package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CreateCustomerDto createCustomerDto){
        return new Customer(
                createCustomerDto.getEmail(),
                createCustomerDto.getFirstName(),
                createCustomerDto.getLastName(),
                createCustomerDto.getAddress(),
                createCustomerDto.getPhoneNumber(),
                createCustomerDto.getPassword());
    }

    public CustomerDto mapToCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getEmail(),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }
}
