package com.example.retailbanking.controller;

import com.example.retailbanking.model.User;
import com.example.retailbanking.service.UserService;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile")
    public String profilePost(@ModelAttribute("user") User newUser, Model model){

        User user =  userService.findByUsername(newUser.getUsername());
        user.setUsername(user.getUsername());
        user.setUsername(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setPhone(user.getPINFL());
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());

        model.addAttribute("user", user);
        userService.saveUser(user);

        return "profile";
    }
}
