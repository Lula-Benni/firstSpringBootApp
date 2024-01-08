package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
@Service
public class ItemServiceEmpL implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    @Override
    public List<Item> fetchItems() {
        return itemRepository.findAll();
    }
    @Override
    public Item fetchItemById(int itemId) {
        return itemRepository.findById(itemId).get();
    }
    @Override
    public String deleteItem(int itemId) {
        itemRepository.deleteById(itemId);
        return "Item with Id "+itemId+" is deleted successfully";
    }
    @Override
    public Item updateItem(int itemId, Item item) {
        Item itemDB = itemRepository.findById(itemId).get();
        Predicate<Object> validate = (input)-> Objects.nonNull(input) && !input.toString().isEmpty();

        if(validate.test(item.getItemName())){
            itemDB.setItemName(item.getItemName());
        }
        if(validate.test(item.getItemDescription())){
            itemDB.setItemDescription(item.getItemDescription());
        }
        if(validate.test(item.getItemPrice())){
            itemDB.setItemPrice(item.getItemPrice());
        }
        if(validate.test(item.getCategory())){
            itemDB.setCategory(item.getCategory());
        }
        if(validate.test(item.getSellerProfile())){
            itemDB.setSellerProfile(item.getSellerProfile());
        }
        return itemRepository.save(itemDB);
    }
}
