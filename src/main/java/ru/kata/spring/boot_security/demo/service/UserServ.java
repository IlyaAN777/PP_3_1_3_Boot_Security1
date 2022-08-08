package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServ extends UserDetailsService {
    List<User> allUsers();

    void saveUser(User user);

    void deleteUser(long userId);
    User findById(long id);

    User getCurrentUser();

    void updateUser(User user);
}
