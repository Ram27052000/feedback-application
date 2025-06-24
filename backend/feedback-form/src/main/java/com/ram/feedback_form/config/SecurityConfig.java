package com.ram.feedback_form.config;

import com.ram.feedback_form.Repository.UserRepository;
import com.ram.feedback_form.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users/create-user").permitAll()
                        .requestMatchers("/api/feedback/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.permitAll().defaultSuccessUrl("/api/feedback/welcome"));
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return new CustomUserDetailsService(userRepository);

        //      INMEMORY DATABASE CONFIGURATIONS

//        String rawPassword = "password123";
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//        UserDetails user = User.withUsername("Alice").password(encodedPassword).roles("USER").build();
//        UserDetails admin =  User.withUsername("Admin").password(encodedPassword).roles("USER").build();
//        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
//
//    private final CustomDetailsService customDetailsService;
//    private final JwtAuthenticationFilter jwtFilter;
//    private final PasswordEncoder passwordEncoder;
//
//    public SecurityConfig(CustomDetailsService customDetailsService,
//                          JwtAuthenticationFilter jwtFilter,
//                          PasswordEncoder passwordEncoder) {
//        this.customDetailsService = customDetailsService;
//        this.jwtFilter = jwtFilter;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/login", "/api/register").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(sess -> sess
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authenticationProvider(daoAuthenticationProvider())
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(customDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManager.class);
//    }
