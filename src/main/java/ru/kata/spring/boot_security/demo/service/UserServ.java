package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServ extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(long id);

    void saveUser(User user);

    void deleteById(Long userId);

    User getCurrentUser();

    void updateUser(User user);

    List<Role> roleList();
}
