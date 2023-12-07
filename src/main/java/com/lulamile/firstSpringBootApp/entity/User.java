package com.lulamile.firstSpringBootApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity
public class User {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Profile profile;
    private Item item;
    /*public User(Profile profile, Item item) {
        this.profile = profile;
        this.item = item;
    }*/
    public Profile getProfile() {
        return profile;
    }
    public Item getItem() {
        return item;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    @Override
    public String toString() {
        return "User{" +
                "profile=" + profile +
                ", item=" + item +
                '}';
    }
}
