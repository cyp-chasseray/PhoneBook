package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.User;
import com.example.phonebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> fetchById(int id) {
        return userRepository.findById(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public User update(User userToUpdate) {

        Optional<User> originalUser = userRepository.findById(userToUpdate.getId());

        if (originalUser.isPresent()) {
            User existingUser = originalUser.get();
            existingUser.setFirstname(userToUpdate.getFirstname());
            existingUser.setLastname(userToUpdate.getLastname());
            existingUser.setPassword(userToUpdate.getPassword());
            existingUser.setPictureUrl(userToUpdate.getPictureUrl());
            existingUser.setContactList(userToUpdate.getContactList());
            return userRepository.save(existingUser);
        }
        throw new IllegalArgumentException("User not found with id: " + userToUpdate.getId());
    }

    public List<Contact> getUserContactList(int userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getContactList();
        }
        throw new IllegalArgumentException("User not found with id: " + userId);
    }

    public List<Contact> searchContactsByKeyword(int userId, String keyword) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Contact> listToFilter = user.getContactList();
            List<Contact> listToReturn = new ArrayList<>();

            for (Contact contact : listToFilter) {
                String[] contactAttributes = {
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAdress(),
                        String.valueOf(contact.getZipCode()),
                        contact.getCity(),
                        contact.getCountry()
                };

                keyword = keyword.toLowerCase();
                for (String attribute : contactAttributes) {
                    if (attribute.toLowerCase().contains(keyword)) {
                        listToReturn.add(contact);
                        break;
                    }
                }
            }
            return listToReturn;
        }
        throw new IllegalArgumentException("User not found with id: " + userId);
    }

}
