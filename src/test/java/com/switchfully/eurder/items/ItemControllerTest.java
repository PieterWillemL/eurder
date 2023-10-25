package com.switchfully.eurder.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.security.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String authorization = "authorization";
    private ItemDto itemDtoRegular;
    private String itemDtoRegularString;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;
    @MockBean
    private SecurityService securityService;

    @BeforeEach
    void setup() throws Exception {
        itemDtoRegular = new ItemDto("Name", 2.5, 10, "Description of this item");

        itemDtoRegularString = objectMapper.writeValueAsString(itemDtoRegular);
    }

    @Test
    void addNewItem_givenValidInput_thenReturnsHttpStatusCreatedAndCorrectReturnType() throws Exception {
        Mockito.when(itemService.addNewItem(itemDtoRegular))
                .thenReturn(itemDtoRegular);

        mockMvc.perform(post("http://localhost:8080/eurder/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(authorization, "")
                        .content(itemDtoRegularString))
                .andExpect(status().isCreated())
                .andExpect(content().json(itemDtoRegularString));

    }

    @Test
    void updateItem_thenReturnsHttpStatusAcceptedAndCorrectReturnType() throws Exception {
        Mockito.when(itemService.updateItem("mockName", itemDtoRegular))
                .thenReturn(itemDtoRegular);

        mockMvc.perform(put("http://localhost:8080/eurder/items/mockName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(authorization, "")
                        .content(itemDtoRegularString))
                .andExpect(status().isAccepted())
                .andExpect(content().json(itemDtoRegularString));
    }

}
