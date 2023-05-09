package com.example.phonebook.web;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.User;
import com.example.phonebook.service.ContactService;
import com.example.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditContactWebController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userservice;
    @GetMapping("/edit-contact/{id}")
    public String getFruitList(@PathVariable("id") int id, Model model) {

        Contact contact = contactService.fetchById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        model.addAttribute("contact", contact);
        return "edit-contact";
    }

    @PostMapping("/edit-contact")
    public String updateContact(@ModelAttribute("contact") Contact updatedContact) {

        contactService.update(updatedContact);
        return "redirect:/contacts/2";
    }
}