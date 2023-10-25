package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item mapToItem(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getPrice(), itemDto.getAmount(), itemDto.getDescription());
    }

    public ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getName(), item.getPrice(), item.getAmount(), item.getDescription());
    }
}
