//package com.ram.feedback_form.Security;
//
//import com.ram.feedback_form.Model.Role;
//import com.ram.feedback_form.Model.User;
//import com.ram.feedback_form.Repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomDetailsService implements UserDetailsService {
//
////    private final UserRepository userRepository;
////
////    public CustomDetailsService(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
////        User user = userRepository.findByEmail(email)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
////        Set<Role> roles = user.getRoles();
////        List<GrantedAuthority> authorities = roles.stream()
////                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
////                .collect(Collectors.toList());
////        return new org.springframework.security.core.userdetails.User(
////                user.getEmail(),
////                user.getPassword(),
////                authorities
////        );
////    }
//}
