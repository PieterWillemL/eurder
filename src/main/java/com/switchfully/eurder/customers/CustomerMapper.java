package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto){
        return new Customer(
                customerDto.getEmail(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getAddress(),
                customerDto.getPhoneNumber());
    }

    public CustomerDto mapToCustomerDto(Customer customer){
        return new CustomerDto()
                .setEmail(customer.getEmail())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setAddress(customer.getAddress())
                .setPhoneNumber(customer.getPhoneNumber());
    }
}
