package com.switchfully.eurder.customers;

import java.util.Objects;

public class Customer {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String password;

    public Customer(String email, String firstName, String lastName, String address, String phoneNumber, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(address, customer.address) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, address, phoneNumber, password);
    }
}
