package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.repository.ItemRepository;
import com.lulamile.firstSpringBootApp.utils.Category;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
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
}