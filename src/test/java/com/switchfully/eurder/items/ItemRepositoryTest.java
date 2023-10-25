package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.ItemAlreadyInDatabaseException;
import com.switchfully.eurder.exceptions.ItemNotInDatabaseException;
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

    @Test
    void givenItemWithNameNotInDatabase_thenThrowsItemNotInDatabaseException(){
        Assertions.assertThatThrownBy(()-> itemRepository.getItemByItemName("NameNotInDatabase"))
                .isInstanceOf(ItemNotInDatabaseException.class)
                .hasMessage("The following item is not present in our database: NameNotInDatabase");
    }

    @Nested
    @DisplayName("Update Item")
    class updateItem{
        @BeforeEach
        void setup(){
            itemRepository.addNewItem(itemToTest);
        }
        @Test
        void givenValidItemNameAndUpdatedItem_thenReturnsThatUpdatedItem(){

            Item updatedItem = new Item("Name", 2.2, 3, "description");

            Assertions.assertThat(itemRepository.updateItem("Name", updatedItem))
                    .isEqualTo(updatedItem);

        }
        @Test
        void givenUpdatedItemNameSameAsItemName_thenItemsContainsUpdatedItem(){
            Item updatedItem = new Item("Name", 2.2, 3, "description");

            itemRepository.updateItem("Name", updatedItem);

            Assertions.assertThat(itemRepository.getItems().values()).contains(updatedItem);
        }

        @Test
        void givenUpdatedItemNameDifferentThanItemName_thenItemsKeysNoLongerContainsItemName(){
            Item updatedItem = new Item("New Name", 2.2, 3, "description");

            itemRepository.updateItem("Name", updatedItem);

            Assertions.assertThat(itemRepository.getItems().containsKey("Name")).isFalse();
        }
    }
}
