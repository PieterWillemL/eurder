package com.switchfully.eurder.customers;

import com.switchfully.eurder.exceptions.EmailAlreadyInDatabaseException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class CustomerRepository {

    private final HashMap<String, Customer> customers = new HashMap<>();

    public Customer createCustomerAccount(Customer customer) {
        if (customers.containsKey(customer.getEmail())) {
            throw new EmailAlreadyInDatabaseException(customer.getEmail());
        }
        customers.put(customer.getEmail(), customer);
        return customer;
    }
}
