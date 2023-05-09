package com.example.phonebook.web;

import com.example.phonebook.entity.User;
import com.example.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterWebController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam("email") String email,
                                      @RequestParam("password") String password,
                                      @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("pictureUrl") String pictureUrl) {

        User user = new User(firstName, lastName, email, password, pictureUrl);
        userService.create(user);

        return "redirect:/login";
    }
}