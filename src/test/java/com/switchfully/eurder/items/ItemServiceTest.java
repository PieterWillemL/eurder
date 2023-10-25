package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    void addNewItem_givenItemDto_thenReturnsCorrectItemDto(){
        Item itemReturnedByRepository = new Item("Name", 2.5, 10, "Description of this item");
        Mockito.when(itemRepository.addNewItem(itemMapper.mapToItem(itemDtoRegular)))
                .thenReturn(itemReturnedByRepository);

        Assertions.assertThat(itemService.addNewItem(itemDtoRegular))
                .isEqualTo(itemDtoRegular);
    }
}

