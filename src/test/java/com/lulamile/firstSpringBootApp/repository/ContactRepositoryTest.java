package com.lulamile.firstSpringBootApp.repository;

import com.lulamile.firstSpringBootApp.entity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .emails("lulabenni45@gmail.com")
                .cellNumber("0768264985")
                .build();
        entityManager.persist(contact);
    }
    @Test
    @DisplayName("Get Profile with valid Email")
    public void findContactByEmailsIgnoreCase() {
        String email = "lulabenni45@gmail.com";
        Contact contact = contactRepository.findContactByEmailsIgnoreCase(email);
        assertEquals(contact.getEmails(),email);
    }
}