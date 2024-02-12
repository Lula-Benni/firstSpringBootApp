package com.lulamile.firstSpringBootApp.entity;

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
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contactId;
    @Column(nullable = false, unique = true)
    private String emails;
    @Column(nullable = true)
    private String cellNumber;
    //@OneToOne(mappedBy = "contact")
    //private Profile profile;
}

