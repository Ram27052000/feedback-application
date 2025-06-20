    package com.ram.feedback_form.controller;

    import com.ram.feedback_form.Model.LoginRequestDTO;
    import com.ram.feedback_form.Model.RegisterRequestDTO;
    import com.ram.feedback_form.Model.User;
    import com.ram.feedback_form.Repository.UserRepository;
    import com.ram.feedback_form.Service.AuthService;
    import com.ram.feedback_form.util.JwtUtils;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api")
    public class AuthController {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtils jwtUtils;
        private final AuthService authService;

        public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthService authService) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.jwtUtils = jwtUtils;
            this.authService = authService;
        }

        @PostMapping("/login")
        public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
            Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());
            if(user.isPresent() && passwordEncoder.matches(loginRequestDTO.getPassword(), user.get().getPassword()))
            {
                String token = jwtUtils.generateToken(loginRequestDTO.getEmail());
                Map<String,String> response = new HashMap<>();
                response.put("jwtToken", token);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        @PostMapping("/register")
        public void register(@RequestBody RegisterRequestDTO registerRequestDTO) throws Exception {
            this.authService.registerUser(registerRequestDTO);
        }
    }
