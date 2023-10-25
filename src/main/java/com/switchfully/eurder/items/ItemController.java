package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eurder/items")
public class ItemController {

    private final ItemService itemService;

    private final SecurityService securityService;

    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addNewItem(@RequestHeader String authorization, @RequestBody ItemDto itemDto){
        securityService.validateAuthorization(authorization, Role.ADMIN);
        return itemService.addNewItem(itemDto);
    }

    @PutMapping("{itemName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@RequestHeader String authorization, @PathVariable String itemName, @RequestBody ItemDto itemDto){
        securityService.validateAuthorization(authorization, Role.ADMIN);
        return itemService.updateItem(itemName, itemDto);
    }


}
