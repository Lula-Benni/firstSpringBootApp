package com.lulamile.firstSpringBootApp.entity;

import com.lulamile.firstSpringBootApp.utils.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
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
    @JoinColumn(name = "contact_Id",referencedColumnName = "contactId")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Contact contact;
    @JoinColumn(name = "address_Id", referencedColumnName = "addressId")
    @ManyToOne(optional = false)
    private Address address;
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateOfBirth;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password_reset_token;
    private LocalDateTime password_reset_token_expDate;
    @OneToMany(mappedBy = "profile")
    private List<Item> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return profileId == profile.profileId && Objects.equals(fullName, profile.fullName) && Objects.equals(userName, profile.userName) && Objects.equals(password, profile.password) && Objects.equals(contact, profile.contact) && Objects.equals(address, profile.address) && Objects.equals(dateOfBirth, profile.dateOfBirth) && gender == profile.gender && Objects.equals(password_reset_token, profile.password_reset_token) && Objects.equals(password_reset_token_expDate, profile.password_reset_token_expDate) && Objects.equals(items, profile.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, fullName, userName, password, contact, address, dateOfBirth, gender, password_reset_token, password_reset_token_expDate, items);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", password_reset_token='" + password_reset_token + '\'' +
                ", password_reset_token_expDate=" + password_reset_token_expDate +
                ", items=" + items +
                '}';
    }
}
