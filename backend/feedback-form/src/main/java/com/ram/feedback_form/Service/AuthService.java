package com.ram.feedback_form.Service;

import com.ram.feedback_form.Model.RegisterRequestDTO;
import com.ram.feedback_form.Model.Role;
import com.ram.feedback_form.Model.User;
import com.ram.feedback_form.Repository.RoleRepository;
import com.ram.feedback_form.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser(RegisterRequestDTO registerRequestDTO) throws Exception{
        boolean isUserExists = userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent();
        if(isUserExists){
            throw new Exception("User Email Already Exists");
        }

        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        User user = new User();
        user.setName(registerRequestDTO.getFirstName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userRepository.save(user);

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

    }
}
