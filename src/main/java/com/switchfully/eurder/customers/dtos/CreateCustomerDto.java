package com.switchfully.eurder.customers.dtos;

public class CreateCustomerDto {

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

    private String password;

    public String getEmail() {
        return email;
    }

    public CreateCustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateCustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CreateCustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CreateCustomerDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateCustomerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateCustomerDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
