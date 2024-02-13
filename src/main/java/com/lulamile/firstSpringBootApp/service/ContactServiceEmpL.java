package com.lulamile.firstSpringBootApp.service;

import com.lulamile.firstSpringBootApp.entity.Contact;
import com.lulamile.firstSpringBootApp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class ContactServiceEmpL implements ContactService{
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
    @Override
    public List<Contact> fetchContacts() {
        return contactRepository.findAll();
    }
    @Override
    public Contact fetchContactById(int contactId) {
        return contactRepository.findById(contactId).get();
    }
    @Override
    public String deleteContact(int contactId) {
        contactRepository.deleteById(contactId);
        return "Contact with id "+contactId+" is successfully deleted";
    }

    @Override
    public Contact updateContact(int contactId,Contact contact) {
        Contact contactDB = contactRepository.findById(contactId).get();
        Predicate<Object> validate = (input) -> Objects.nonNull(input) && !input.toString().isEmpty();
        if(validate.test(contact.getEmails())){
            contactDB.setEmails(contact.getEmails());
        }
        if(validate.test(contact.getCellNumber())){
            contactDB.setCellNumber(contact.getCellNumber());
        }
        return contactRepository.save(contactDB);
    }
    @Override
    public Contact fetchContactByEmail(String emails){
        return contactRepository.findContactByEmailsIgnoreCase(emails);
    }
}
