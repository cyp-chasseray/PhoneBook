package com.example.phonebook.web;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.service.ContactService;
import com.example.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.phonebook.entity.User;

import java.util.Optional;

@Controller
public class AddContactWebController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @GetMapping("/add-contact")
    public String getFruitList() {
        return "add-contact";
    }

    @PostMapping("/add-contact")
    public String addContact(@ModelAttribute("contact") Contact newContact) {
        Contact addedContact = contactService.create(newContact);
        int addedContactId = addedContact.getId();
        Optional<User> userOptional = userService.fetchById(2);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            contactService.assignUserToContact(addedContactId, user);
        }
        return "redirect:/contacts/2";
        }

    }
