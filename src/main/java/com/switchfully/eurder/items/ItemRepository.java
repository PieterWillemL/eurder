package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.ItemAlreadyInDatabaseException;
import com.switchfully.eurder.exceptions.ItemNotInDatabaseException;
import com.switchfully.eurder.orders.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ItemRepository {

    private final HashMap<String, Item> items = new HashMap<>();

    public Item addNewItem(Item item) {
        if (items.containsKey(item.getName())) {
            throw new ItemAlreadyInDatabaseException(item);
        }
        items.put(item.getName(), item);
        return item;
    }

    public Item getItemByItemName(String itemName) {
        if (!items.containsKey(itemName)) {
            throw new ItemNotInDatabaseException(itemName);
        }
        return items.get(itemName);
    }

    public Item updateItem(String itemName, Item updatedItem) {
        items.put(updatedItem.getName(), updatedItem);
        if (!itemName.equals(updatedItem.getName())) {
            items.remove(itemName);
        }
        return updatedItem;
    }

    public void updateAmounts(Order order) {
        order.getItemGroupList()
                .forEach(itemGroup -> items.get(itemGroup.getItemName()).setAmount(Math.max(items.get(itemGroup.getItemName()).getAmount() - itemGroup.getAmount(), 0)));
    }

    public HashMap<String, Item> getItems() {
        return items;
    }
}
