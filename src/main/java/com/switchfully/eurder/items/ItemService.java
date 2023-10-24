package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addNewItem(ItemDto itemDto) {
        return itemMapper.mapToItemDto(itemRepository.addNewItem(itemMapper.mapToItem(itemDto)));
    }
}
