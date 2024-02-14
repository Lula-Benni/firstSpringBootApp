package com.lulamile.firstSpringBootApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return contactId == contact.contactId && Objects.equals(emails, contact.emails) && Objects.equals(cellNumber, contact.cellNumber);
    }


    @Override
    public int hashCode() {
        return Objects.hash(contactId, emails, cellNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", emails='" + emails + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                '}';
    }
}

