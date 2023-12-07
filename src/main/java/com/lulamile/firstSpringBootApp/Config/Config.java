package com.lulamile.firstSpringBootApp.Config;

import com.lulamile.firstSpringBootApp.entity.Item;
import com.lulamile.firstSpringBootApp.entity.Profile;
import com.lulamile.firstSpringBootApp.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
public class Config {
    /*@Bean
    public User getUser(Profile profile, Item item){
        return new User(profile,item);
    }
    *//*@Bean
    public Profile getProfile(@Value("Lula") String user, @Value("lulabenni45@gmail.com") String email, @Value("0783432345") String cellNumber, @Value("123456") String password, BCryptPasswordEncoder bCryptPasswordEncoder){
        return new Profile(user,email,cellNumber,password,bCryptPasswordEncoder);
    }*//*
    @Bean
    public Item getItem(@Value("LB12345") String itemId, @Value("Samsung A03") String itemName, @Value("750.00") Double itemPrice, Item.Category category){
        return new Item(itemId,itemName,itemPrice,category);
    }
    @Bean
    public Item.Category getCategory(){
        return Item.Category.CELLPHONE;
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }*/
}
