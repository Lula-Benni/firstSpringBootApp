package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.repository.ItemRepository;
import com.lulamile.firstSpringBootApp.utils.Category;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @MockBean
    private ItemRepository itemRepository;
    private Category category;
    @ParameterizedTest
    @EnumSource(names = {"BOOKS","CELLPHONE","COMPUTERS"})
    void fetchItemsByCategory(Category category) {
        Mockito.when(itemRepository.findItemsByCategory(category)).thenReturn(getItems(category));
        if(itemRepository.findItemsByCategory(category).isEmpty()){
            Exception exception = assertThrows(EntityNotFoundException.class, () -> itemService.fetchItemsByCategory(category),"No item found for that category");
            assertEquals("No item found for that category", exception.getMessage());
        }else{
        List<Item> found = itemService.fetchItemsByCategory(category);
        assertFalse(found.isEmpty());}
    }
    private  List<Item> getItems(Category category){
        return  new ArrayList<>(Arrays.asList(
                Item.builder().itemName("Iphone 7").itemDescription("still new").itemPrice(3000.0).category(Category.CELLPHONE).build(),
                Item.builder().itemName("Samsung").itemDescription("still new").itemPrice(1000.0).category(Category.CELLPHONE).build(),
                Item.builder().itemName("Akwaba").itemDescription("still new").itemPrice(300.0).category(Category.BOOKS).build()
        )).stream().filter(cat->cat.getCategory()==(category)).toList();
    }
    @ParameterizedTest
    @MethodSource("getTestCasesForFetchItemsByName")
    void fetchItemByName(String name) {
        List<Item> searchedItems = getSearchedItems(name);
        Mockito.when(itemRepository.findItemsByItemNameIgnoreCaseContaining(name)).thenReturn(searchedItems);

        if (searchedItems.isEmpty()) {
            assertThrows(EntityNotFoundException.class, () -> itemService.fetchItemByItemNameIgnoreCase(name),"No item found");
        } else {
            List<Item> found = itemService.fetchItemByItemNameIgnoreCase(name);
            assertFalse(found.isEmpty());
        }
    }
    private List<Item> getSearchedItems(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList(); // return an empty list for null or empty names
        }
        return Stream.of(
                Item.builder().itemName("Iphone 7").itemDescription("still new").itemPrice(3000.0).category(Category.CELLPHONE).build(),
                Item.builder().itemName("Samsung").itemDescription("still new").itemPrice(1000.0).category(Category.CELLPHONE).build(),
                Item.builder().itemName("Akwaba").itemDescription("still new").itemPrice(300.0).category(Category.BOOKS).build(),
                Item.builder().itemName("The song of ice and fire").itemDescription("still new").itemPrice(300.0).category(Category.BOOKS).build()
        ).filter(item -> item.getItemName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    private Stream<String> getTestCasesForFetchItemsByName() {
        return Stream.of("the song of ice and fire", "song", "iphone", "ice", null, " ", ""); // include empty string
    }
}