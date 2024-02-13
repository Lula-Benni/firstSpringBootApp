package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact saveContact(Contact contact);

    List<Contact> fetchContacts();

    Contact fetchContactById(int contactId);

    String deleteContact(int contactId);

    Contact updateContact(int contactId,Contact contact);
    public Contact fetchContactByEmail(String emails);
}
