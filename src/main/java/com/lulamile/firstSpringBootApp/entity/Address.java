package com.lulamile.firstSpringBootApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressId;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String additionalDetails;
    @OneToMany(mappedBy = "address")
    private List<Profile> profiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return addressId == address.addressId && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode) && Objects.equals(state, address.state) && Objects.equals(country, address.country) && Objects.equals(additionalDetails, address.additionalDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, city, postalCode, state, country, additionalDetails);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", additionalDetails='" + additionalDetails + '\'' +
                '}';
    }
}
