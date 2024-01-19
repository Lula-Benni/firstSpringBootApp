package com.lulamile.firstSpringBootApp.entity;

import com.lulamile.firstSpringBootApp.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(nullable = false)
    private String password;
    @NonNull
    @JoinColumn(name = "contact_Id",referencedColumnName = "contactId")
    @OneToOne(optional = false)
    private Contact contact;
    @NonNull
    @JoinColumn(name = "address_Id", referencedColumnName = "addressId")
    @ManyToOne(optional = false)
    private Address address;
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOfBirth;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    /*@JoinColumn(name="itemId",nullable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;*/
}
