package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("/create")
    public String createUser(User user, Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("user", user);
        model.addAttribute("listUsers", userList);
        return "create";
    }

    @PostMapping("/create")
    public String create(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/info/{id}")
    public String userDetailsPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "info";
    }

    @GetMapping("/update/{id}")
    public String updateUserDetailsPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


}