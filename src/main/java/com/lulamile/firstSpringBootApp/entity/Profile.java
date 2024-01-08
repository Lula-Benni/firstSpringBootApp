package com.lulamile.firstSpringBootApp.entity;

import com.lulamile.firstSpringBootApp.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int profileId;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Length(max=12,min=7)
    @Column(nullable = false, length = 12)
    private String password;
    @NonNull
    @JoinColumn(name = "contact_Id")
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contact contact;
    @NonNull
    @JoinColumn(name = "address_Id")
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private Address address;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JoinColumn(name="itemId",nullable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
}
