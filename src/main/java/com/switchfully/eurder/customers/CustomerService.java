package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    public CustomerDto createCustomerAccount(CreateCustomerDto createCustomerDto) {
        return customerMapper.mapToCustomerDto(customerRepository.createCustomerAccount(customerMapper.mapToCustomer(createCustomerDto)));
    }
}
