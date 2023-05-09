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
public class EditProfileWebController {
    @Autowired
    private UserService userService;

    @GetMapping("/edit-profile/{id}")
    public String editProfile(@PathVariable("id") int id, Model model) {

        User user = userService.fetchById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
        model.addAttribute("user", user);
        return "edit-profile";
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute("user") User updatedUser) {

        userService.update(updatedUser);
        return "redirect:/contacts/2";
    }
}
