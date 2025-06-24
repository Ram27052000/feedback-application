package com.ram.feedback_form.controller;

import com.ram.feedback_form.Model.User;
import com.ram.feedback_form.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) throws Exception {
        boolean user_1 = userRepository.findByEmail(user.getEmail()).isPresent();
        if(user_1) {
            throw new Exception("User Already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
