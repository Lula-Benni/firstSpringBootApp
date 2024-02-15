package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServiceTest {
    @MockBean
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;
    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .contactId(1)
                .cellNumber("0768264985")
                .emails("spha@gmail.com")
                .build();

        Mockito.when(contactRepository.findContactByEmailsIgnoreCase("spha@gmail.com"))
                .thenReturn(contact);
    }
    @Test
    void fetchContactByEmail() {
        String email = "spha@gmail.com";
        Contact found = contactService.fetchContactByEmail(email);
        assertEquals(email,found.getEmails());
    }
}