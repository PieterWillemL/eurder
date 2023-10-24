package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.ItemAlreadyInDatabaseException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ItemRepositoryTest {

    private ItemRepository itemRepository;

    private Item itemToTest;

    @BeforeEach
    void setup(){
        itemRepository = new ItemRepository();
        itemToTest = new Item("Name", 2.5, 10, "Description of this item");
    }

    @Nested
    @DisplayName("Add New Item")
    class AddNewItem{
        @Test
        void givenValidNewItem_thenReturnsThisItem(){
            Assertions.assertThat(itemRepository.addNewItem(itemToTest))
                    .isEqualTo(itemToTest);
        }

        @Test
        void givenItemWithNameAlreadyInDatabase_thenThrowsItemAlreadyInDatabaseException(){
            itemRepository.addNewItem(itemToTest);
            Item itemWithSameName = new Item("Name", 3.1, 5, "Other description");
            Assertions.assertThatThrownBy(()->itemRepository.addNewItem(itemWithSameName))
                    .isInstanceOf(ItemAlreadyInDatabaseException.class)
                    .hasMessage("Our database already contains following item:\n" + itemWithSameName);
        }
    }
}
