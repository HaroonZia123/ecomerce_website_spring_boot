/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controllers;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;
    boolean isAdmin;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/home")
    public String Home(Model model) {
        model.addAttribute("admin",isAdmin);
        return "template"; // This assumes you have an HTML template named "add.html" for adding a new customer.
    }

    @GetMapping("/")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser == null) {
            model.addAttribute("error", "User not found");
            return "user/login"; // Return to the registration page with an error message
        }
        if (!existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "incorrect password");
            return "user/login";
        }

        if (user.getUsername().equals("admin")) {
            session.setAttribute("admin", true);
        } else {
            session.setAttribute("admin", false);
        }


        return "template"; // Redirect to the login page after registration
        }

        @GetMapping("/register")
        public String register
        
            () {
        return "user/register";
        }

        @PostMapping("/register")
        public String registerUser
        (@ModelAttribute("user")
        User user,Model model
            ) {
            
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            model.addAttribute("error", "Already user exit by this name");
            return "user/register"; // Return to the registration page with an error message
        }
        userService.registerUser(user.getUsername(), user.getPassword());
            // System.out.println(user.getUsername());
            return "redirect:/"; // Redirect to the login page after registration
        }

    }
