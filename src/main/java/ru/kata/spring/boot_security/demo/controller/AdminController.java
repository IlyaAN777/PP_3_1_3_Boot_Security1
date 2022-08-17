package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/create")
    public String createUser(User user, String roleId) {
        user.setRoles(roleService.findRolesByName(roleId));
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping
    public String adminList(Model model, Principal principal) {
        User admin = userService.getUserByUsername(principal.getName());
        model.addAttribute("adminUsername", admin.getUsername());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("adminRoles", admin.getRolesName());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, String roleId) {
        user.setRoles(roleService.findRolesByName(roleId));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long Id) {
        userService.deleteById(Id);
        return "redirect:/admin";
    }


    @GetMapping("/adminInf")
    public String index(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "admin-info";
    }

}