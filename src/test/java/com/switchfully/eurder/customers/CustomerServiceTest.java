package com.switchfully.eurder.customers;

import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerService customerService;
    private final CustomerMapper customerMapper = new CustomerMapper();
    @Mock
    private CustomerRepository customerRepository;
    private CustomerDto customerDtoRegular;
    private CreateCustomerDto createCustomerDtoRegular;

    @BeforeEach
    void setup(){
        customerService = new CustomerService(customerRepository, customerMapper);
        customerDtoRegular = new CustomerDto("first@email.com", "firstname lastname", "street 1, City", "+32490123456");

        createCustomerDtoRegular = new CreateCustomerDto("first@email.com", "firstname", "lastname", "street 1, City", "+32490123456", "password");


    }

    @Test
    void createCustomerAccount_givenCreateCustomerDto_thenReturnsCorrectCustomerDto(){
        Customer customerReturnedByRepository = new Customer("first@email.com", "firstname", "lastname", "street 1, City", "+32490123456","password");
        Mockito.when(customerRepository.createCustomerAccount(customerMapper.mapToCustomer(createCustomerDtoRegular)))
                .thenReturn(customerReturnedByRepository);

        Assertions.assertThat(customerService.createCustomerAccount(createCustomerDtoRegular))
                .isEqualTo(customerDtoRegular);
    }
}
