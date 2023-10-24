package com.switchfully.eurder.customers;

import com.switchfully.eurder.exceptions.EmailAlreadyInDatabaseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CustomerRepositoryTest {

    private CustomerRepository customerRepository;
    private Customer customerToTest;


    @BeforeEach
    void setup(){
        customerRepository = new CustomerRepository();
        customerToTest = new Customer("first@email.com", "firstname", "lastname", "street 1, City", "+32490123456","password");
    }

    @Nested
    @DisplayName("Create Customer Account")
    class CreateCustomerAccount{
        @Test
        void givenValidNewCustomer_thenReturnsThisCustomer(){
            Assertions.assertThat(customerRepository.createCustomerAccount(customerToTest))
                    .isEqualTo(customerToTest);
        }

        @Test
        void givenNewCustomerWithEmailAlreadyInDatabase_thenThrowsEmailAlreadyInDatabaseException(){
            customerRepository.createCustomerAccount(customerToTest);
            Customer customerWithSameEmail = new Customer("first@email.com", "f", "l", "s 1, City", "+32499654321", "password");
            Assertions.assertThatThrownBy(()->customerRepository.createCustomerAccount(customerWithSameEmail))
                    .isInstanceOf(EmailAlreadyInDatabaseException.class)
                    .hasMessage("A Customer Account for first@email.com already exists.");
        }

    }

}
