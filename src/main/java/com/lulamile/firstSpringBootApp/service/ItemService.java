package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.utils.Category;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    public Item saveItem(Item item);

    public List<Item> fetchItems();

    Item fetchItemById(int itemId);

    String deleteItem(int itemId);

    Item updateItem(int itemId, Item item);
    List<Item> fetchItemsByCategory(Category category);
    Optional<Item> fetchItemByName(String name);
}
