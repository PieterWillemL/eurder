package com.switchfully.eurder.items;

import com.switchfully.eurder.exceptions.ItemAlreadyInDatabaseException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ItemRepository {

    private final HashMap<String, Item> items = new HashMap<>();
    public Item addNewItem(Item item) {
        if(items.containsKey(item.getName())){
            throw new ItemAlreadyInDatabaseException(item);
        }
        items.put(item.getName(), item);
        return item;
    }
}
