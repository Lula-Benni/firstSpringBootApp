package com.lulamile.firstSpringBootApp.service;
import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {
    @Mock
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
        Contact contactTwo = Contact.builder()
                .contactId(1)
                .cellNumber("0768264985")
                .emails("lulabenni45@gmail.com")
                .build();
        contactRepository.save(contact);
        contactRepository.save(contactTwo);
    }

    @Test
    @DisplayName("Fetch contact by valid email")
    void fetchContactByEmail() {
        String email = "spha@gmail.com";
        Contact found = contactService.fetchContactByEmail(email);
        assertEquals(email,found.getEmails());
    }
    @Test
    @DisplayName("Trying to Fetch Contact by null or Invalid email")
    void fetchContactByNullEmail(){
        Contact found = contactService.fetchContactByEmail("pay");
        assertNull(found);
    }
}