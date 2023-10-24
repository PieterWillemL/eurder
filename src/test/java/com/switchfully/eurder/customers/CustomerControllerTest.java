package com.switchfully.eurder.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.customers.dtos.CustomerDto;
import com.switchfully.eurder.security.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    private CustomerDto customerDtoRegular;
    private String customerDtoRegularString;
    private CreateCustomerDto createCustomerDtoRegular;
    private String createCustomerDtoRegularString;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private SecurityService securityService;

    @BeforeEach
    void setup() throws Exception {
        customerDtoRegular = new CustomerDto()
                .setEmail("first@email.com")
                .setName("firstname lastname")
                .setAddress("street 1, City")
                .setPhoneNumber("+32490123456");

        createCustomerDtoRegular = new CreateCustomerDto()
                .setEmail("first@email.com")
                .setFirstName("firstname")
                .setLastName("lastname")
                .setAddress("street 1, City")
                .setPhoneNumber("+32490123456")
                .setPassword("password");

        customerDtoRegularString = objectMapper.writeValueAsString(customerDtoRegular);

        createCustomerDtoRegularString = objectMapper.writeValueAsString(createCustomerDtoRegular);
    }

    @Test
    void createCustomerAccount_givenValidInput_thenReturnsHttpStatusCreatedAndCorrectReturnType() throws Exception{

        Mockito.when(customerService.createCustomerAccount(createCustomerDtoRegular)).thenReturn(customerDtoRegular);

        mockMvc.perform(post("http://localhost:8080/eurder/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createCustomerDtoRegularString))
                .andExpect(status().isCreated())
                .andExpect(content().json(customerDtoRegularString));
    }
}
