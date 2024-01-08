package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Item;

import java.util.List;

public interface ItemService {
    public Item saveItem(Item item);

    public List<Item> fetchItems();

    Item fetchItemById(int itemId);

    String deleteItem(int itemId);

    Item updateItem(int itemId, Item item);
}
