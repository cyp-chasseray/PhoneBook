package com.example.phonebook.web;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.service.ContactService;
import com.example.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ContactWebController {
    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{userId}")
    public String getContactList(@PathVariable int userId, Model model) {
        List<Contact> contacts = userService.getUserContactList(userId);
        model.addAttribute("contacts", contacts);
        return "contact-list";
    }

    @GetMapping("/contacts/{userId}/search")
    public String searchContacts(@PathVariable int userId, @RequestParam("keyword") String keyword, Model model) {
        List<Contact> contacts = userService.searchContactsByKeyword(userId, keyword);
        model.addAttribute("contacts", contacts);
        return "contact-list";
    }

    @GetMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable int contactId) {
        contactService.deleteById(contactId);
        return "redirect:/contacts/2";
    }
}
