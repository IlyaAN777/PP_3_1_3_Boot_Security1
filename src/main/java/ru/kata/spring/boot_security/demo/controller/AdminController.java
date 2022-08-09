package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

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
        model.addAttribute("userList", userService.allUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

//    @GetMapping("/user-create")
//    public String createUserForm(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

    @GetMapping("/create")
    public String createUserForm(User user){
        return "create";
    }

    @PostMapping("/create")
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/info/{id}")
    public String userDetailsPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "info";
    }

    @GetMapping("/update/{id}")
    public String updateUserDetailsPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}