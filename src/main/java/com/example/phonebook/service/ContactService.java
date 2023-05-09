package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.User;
import com.example.phonebook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    public List<Contact> fetchAll() {
        return contactRepository.findAll();
    }
    public Optional<Contact> fetchById(int id){
        return contactRepository.findById(id);
    }
    public Contact create(Contact contact){
        return contactRepository.save(contact);
    }

    public Contact update(Contact contactToUpdate) {
        Optional<Contact> originalContact = contactRepository.findById(contactToUpdate.getId());
        if (originalContact.isPresent()) {
            Contact existingContact = originalContact.get();
            existingContact.setFirstName(contactToUpdate.getFirstName());
            existingContact.setLastName(contactToUpdate.getLastName());
            existingContact.setAdress(contactToUpdate.getAdress());
            existingContact.setZipCode(contactToUpdate.getZipCode());
            existingContact.setCity(contactToUpdate.getCity());
            existingContact.setCountry(contactToUpdate.getCountry());
            return contactRepository.save(existingContact);
        }
        throw new IllegalArgumentException("User not found with id: " + contactToUpdate.getId());
    }

    public void deleteById(int id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Contact not found with id: " + id);
        }
    }


}

