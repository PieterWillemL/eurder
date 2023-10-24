package com.switchfully.eurder.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.eurder.customers.dtos.CreateCustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    public static final CreateCustomerDto CUSTOMER_DTO = new CreateCustomerDto()
            .setEmail("first@email.com")
            .setFirstName("firstname")
            .setLastName("lastname")
            .setAddress("street 1, City")
            .setPhoneNumber("+32490123456");

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    @Test
    void createCustomerAccount_givenValidInput_thenReturnsHttpStatusCreatedAndCorrectReturnType() throws Exception{
        String customerDtoString = objectMapper.writeValueAsString(CUSTOMER_DTO);
    }
}
