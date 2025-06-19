package com.ram.feedback_form.Service;

import com.ram.feedback_form.Model.User;
import com.ram.feedback_form.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void checkUser(String email, String name, String password) throws Exception{
        boolean isUserExists = userRepository.findByEmail(email).isPresent();
        if(isUserExists){
            throw new Exception("User Email Already Exists");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

    }
}
