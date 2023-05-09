package com.example.phonebook.service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.User;
import com.example.phonebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
