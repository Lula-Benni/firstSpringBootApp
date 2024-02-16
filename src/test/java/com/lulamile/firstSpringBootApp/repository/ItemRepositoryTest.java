package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.utils.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @BeforeEach
    void setUp() {
        Item item = Item.builder()
                .itemName("Iphone 7")
                .itemPrice(3000.0)
                .category(Category.CELLPHONE)
                .itemDescription("still new")
                .build();

        Item itemTwo = Item.builder()
                .itemName("Samsung A03")
                .itemPrice(1000.0)
                .category(Category.CELLPHONE)
                .itemDescription("still new")
                .build();

        Item itemThree = Item.builder()
                .itemName("The song of Ice and Fire")
                .itemPrice(300.0)
                .category(Category.BOOKS)
                .itemDescription("still new")
                .build();
        itemRepository.save(item);
        itemRepository.save(itemTwo);
        itemRepository.save(itemThree);
    }
    @Test
    @DisplayName("Filter Items by category")
    void findItemsByCategory() {
        Category category = Category.CELLPHONE;
        List<Item> found = itemRepository.findItemsByCategory(category);
        assertEquals(2,found.size());
    }
}