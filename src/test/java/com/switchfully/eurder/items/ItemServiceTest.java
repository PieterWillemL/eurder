package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    private final ItemMapper itemMapper = new ItemMapper();
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepository;

    private ItemDto itemDtoRegular;

    @BeforeEach
    void setup() {
        itemService = new ItemService(itemRepository, itemMapper);
        itemDtoRegular = new ItemDto("Name", 2.5, 10, "Description of this item");
    }

    @Test
    void addNewItem_givenItemDto_thenReturnsCorrectItemDto() {
        Item itemReturnedByRepository = new Item("Name", 2.5, 10, "Description of this item");
        Mockito.when(itemRepository.addNewItem(itemMapper.mapToItem(itemDtoRegular)))
                .thenReturn(itemReturnedByRepository);

        Assertions.assertThat(itemService.addNewItem(itemDtoRegular))
                .isEqualTo(itemDtoRegular);
    }

    @Nested
    @DisplayName("Update Item")
    class updateItem {
        @Test
        void updateItem_givenItemDtoWithSomeValuesNull_thenReturnsCorrectItemDto() {
            ItemDto itemDtoToTest = new ItemDto(null, null, 130, "new description");
            Mockito.when(itemRepository.getItemByItemName("ItemName"))
                    .thenReturn(new Item("ItemName", 14.50, 90, "old description"));
            Mockito.when(itemRepository.updateItem("ItemName", new Item("ItemName", 14.50, 130, "new description")))
                    .thenReturn(new Item("ItemName", 14.50, 130, "new description"));
            ItemDto expectedResult = new ItemDto("ItemName", 14.50, 130, "new description");


            Assertions.assertThat(itemService.updateItem("ItemName", itemDtoToTest))
                    .isEqualTo(expectedResult);
        }

        @Test
        void updateItem_givenItemDtoWithOtherValuesNull_thenReturnsCorrectItemDto() {
            ItemDto itemDtoToTest = new ItemDto("newItemName", 15.00, 90, "old description");
            Mockito.when(itemRepository.getItemByItemName("ItemName"))
                    .thenReturn(new Item("ItemName", 14.50, 90, "old description"));
            Mockito.when(itemRepository.updateItem("ItemName", new Item("newItemName", 15.00, 90, "old description")))
                    .thenReturn(new Item("newItemName", 15.00, 90, "old description"));
            ItemDto expectedResult = new ItemDto("newItemName", 15.00, 90, "old description");


            Assertions.assertThat(itemService.updateItem("ItemName", itemDtoToTest))
                    .isEqualTo(expectedResult);
        }
    }
}

