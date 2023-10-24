package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addNewItem(ItemDto itemDto) {
        return itemMapper.mapToItemDto(itemRepository.addNewItem(itemMapper.mapToItem(itemDto)));
    }

    public Item getItemByItemName(String itemName) {
        return itemRepository.getItemByItemName(itemName);
    }
}
