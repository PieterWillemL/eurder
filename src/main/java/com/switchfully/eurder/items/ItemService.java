package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.orders.Order;
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

    public ItemDto updateItem(String itemName, ItemDto itemDto) {
        Item itemToUpdate = itemRepository.getItemByItemName(itemName);
        Item updatedItem = new Item(
                itemDto.getName() == null ? itemToUpdate.getName() : itemDto.getName(),
                itemDto.getPrice() == null ? itemToUpdate.getPrice() : itemDto.getPrice(),
                itemDto.getAmount() == null ? itemToUpdate.getAmount() : itemDto.getAmount(),
                itemDto.getDescription() == null ? itemToUpdate.getDescription() : itemDto.getDescription()
        );
        return itemMapper.mapToItemDto(itemRepository.updateItem(itemName, updatedItem));
    }

    public void updateAmounts(Order order){
        itemRepository.updateAmounts(order);
    }
}
