package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto getCustomerByEmail(String customerEmail) {
        return customerMapper.mapToCustomerDto(customerRepository.getCustomerByEmail(customerEmail));
    }

    public CustomerDto createCustomerAccount(CreateCustomerDto createCustomerDto) {
        return customerMapper.mapToCustomerDto(customerRepository.createCustomerAccount(customerMapper.mapToCustomer(createCustomerDto)));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.getCustomers().values().stream()
                .map(customerMapper::mapToCustomerDto)
                .toList();
    }
}
