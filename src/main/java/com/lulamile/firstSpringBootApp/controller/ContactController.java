package com.lulamile.firstSpringBootApp.controller;

import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @PostMapping("/contact")
    public Contact saveContact(@RequestBody Contact contact){return contactService.saveContact(contact);}
    @GetMapping("/contact")
    public List<Contact> fetchContacts(){return contactService.fetchContacts();}
    @GetMapping("/contact/{id}")
    public Contact fetchContactById(@PathVariable("id") int contactId){return contactService.fetchContactById(contactId);}
    @DeleteMapping("/contact/{id}")
    public String deleteContact(@PathVariable("id") int contactId){return contactService.deleteContact(contactId);}
    @PutMapping("/contact/{id}")
    public Contact updateContact(@PathVariable("id") int contactId,@RequestBody Contact contact){return contactService.updateContact(contactId,contact);}
}
