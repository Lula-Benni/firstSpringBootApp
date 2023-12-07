package com.lulamile.firstSpringBootApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//@Entity
public class Item {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String itemId;
    private String itemName;
    private Double itemPrice;
    private Category category;
    public enum Category{
        CELLPHONE, ACCESSORIES, COMPUTERS, VIDEO_GAMES,MUSICAL_INSTRUMENTS, BOOKS
    }
    /*public Item(){
        itemId="LB12345";
        itemName="Samsung A03";
        itemPrice=750.40;
        category=Category.CELLPHONE;
    }
    public Item(String itemId, String itemName, Double itemPrice, Category category) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.category = category;
    }*/
    public String getItemId() {
        return itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public Double getItemPrice() {
        return itemPrice;
    }
    public Category getCategory() {
        return category;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", category=" + category +
                '}';
    }
}
