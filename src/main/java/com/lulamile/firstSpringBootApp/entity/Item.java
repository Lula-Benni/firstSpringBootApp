package com.lulamile.firstSpringBootApp.entity;

import com.lulamile.firstSpringBootApp.utils.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
}
