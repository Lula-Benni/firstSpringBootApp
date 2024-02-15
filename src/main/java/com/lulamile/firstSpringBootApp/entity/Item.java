package com.lulamile.firstSpringBootApp.entity;

import com.lulamile.firstSpringBootApp.utils.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    @Column(nullable = false)
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @JoinColumn(name="profile_Id",nullable = true)
    @ManyToOne
    private Profile profile;

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemDescription, itemPrice, category);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice +
                ", category=" + category +
                '}';
    }
}
