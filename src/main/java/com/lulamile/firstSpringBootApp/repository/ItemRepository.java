package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.utils.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    public List<Item> findItemsByCategory(Category category);
    public Optional<Item> findItemByItemName(String itemName);
}

