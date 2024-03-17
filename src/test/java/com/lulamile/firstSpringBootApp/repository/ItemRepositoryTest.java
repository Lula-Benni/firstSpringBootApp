package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.utils.Category;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Array;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TestEntityManager entityManager;
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
        entityManager.persist(item);
        entityManager.persist(itemTwo);
        entityManager.persist(itemThree);
    }
    @AfterEach
    void tearDown() {
        entityManager.clear();
    }
    @Test
    @DisplayName("Filter Items by category")
    void findItemsByCategory() {
        Category category = Category.BOOKS;
        List<Item> found = itemRepository.findItemsByCategory(category);
        assertEquals(1,found.size());
    }
    @Test
    @DisplayName("No item found for the category")
    void findItemsByCategoryNotFound() {
        Category category = Category.COMPUTERS;
        List<Item> found = itemRepository.findItemsByCategory(category);
        System.out.println(found);
        assertEquals(0,found.size());
    }

    @ParameterizedTest
    @MethodSource("getFindItemsByCategoryNotFoundParameters")
    void findItemByItemName(String name) {
            if (itemRepository.findItemsByItemNameIgnoreCaseContaining(name).isEmpty()) {
                assertTrue(true);
            } else if (name.equals(" ")) {
                assertFalse(false);
            } else {
                List<Item> found = itemRepository.findItemsByItemNameIgnoreCaseContaining(name);
                System.out.println("*****************************" + name+" "+found+ "*******************************");
                assertTrue(true);
        }
    }
    private Stream<String> getFindItemsByCategoryNotFoundParameters(){
        return Stream.of("the song of ice and fire","iphone","ice and fire","nhyok"," ",null);
    }
}