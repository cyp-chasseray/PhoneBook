package com.example.phonebook.web;

import com.example.phonebook.api.UserController;
import com.example.phonebook.entity.Contact;
import com.example.phonebook.entity.User;
import com.example.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginWebController {

    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes) {

        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                int userId = user.getId();
                return "redirect:/contacts/" + userId;
            }
        }
        redirectAttributes.addFlashAttribute("error", "Invalid email or password");
        return "redirect:/login";
    }
}






