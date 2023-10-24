package com.switchfully.eurder.customers.dtos;

public class CustomerDto {

    private String email;

    private String name;

    private String address;

    private String phoneNumber;

    public String getEmail() {
        return email;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;
    }



    public String getName() {
        return name;
    }

    public CustomerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
