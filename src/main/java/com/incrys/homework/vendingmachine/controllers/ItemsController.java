package com.incrys.homework.vendingmachine.controllers;

import com.incrys.homework.vendingmachine.dao.Items;
import com.incrys.homework.vendingmachine.exceptions.NotAnExistingItemException;
import com.incrys.homework.vendingmachine.services.IItemsService;
import com.incrys.homework.vendingmachine.services.ItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ItemsController {

    private ItemsServiceImpl itemsService;

    @Autowired
    public ItemsController(ItemsServiceImpl itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/items")
    public List<Items> showItems(){
        return itemsService.findAll();
    }

    @GetMapping("/items/{itemId}")
    public Items showItems(@PathVariable int itemId){

        Items item = itemsService.findItemById(itemId);

        if (item == null){
            throw new NotAnExistingItemException("Could not find the Item with the ID: " + itemId);
        }

        return itemsService.findItemById(itemId);
    }

    @PostMapping("/items")
    public Items addItem(@Valid @RequestBody Items item){

        itemsService.addItem(item);
        return item;
    }

    @DeleteMapping("/items/{itemId}")
    public String deleteItemById(@PathVariable int itemId){

        Items item = itemsService.findItemById(itemId);

        if (item == null){
            throw new NotAnExistingItemException("Could not find the Item with the ID: " + itemId);
        }

        itemsService.deleteItemByID(itemId);

        return "Item was successfully deleted!";
    }

    @PutMapping("/items")
    public Items updateItem( @Valid @RequestBody Items item){

        itemsService.updateItem(item);
        return item;
    }

    @GetMapping("/items/buy/{itemId}")
    public String buyItem(@PathVariable int itemId){

        Optional<Items> isItem = Optional.ofNullable(itemsService.findItemById(itemId));

        if (isItem.isEmpty()){
            throw new NotAnExistingItemException("Could not find the Item with the ID: " + itemId);
        }

        itemsService.buyItemById(itemId);

        return "Ati cumparat produsul " + isItem.get().getName() + " cu succes!" + "\n" +
                "Restul dvs. este: " + "\n" +
                itemsService.getClientChangeBillsMap() + "\n" +
                itemsService.getClientChangeCoinsMap();
    }

    @GetMapping("/credit")
    public String showCredit(){
        return itemsService.getTotalCredit().toString();
    }

}
