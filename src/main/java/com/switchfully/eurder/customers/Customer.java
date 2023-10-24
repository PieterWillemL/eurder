package com.switchfully.eurder.customers;

public class Customer {

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public Customer(String email, String firstName, String lastName, String address, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
}
