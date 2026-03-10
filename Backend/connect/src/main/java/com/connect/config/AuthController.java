package com.connect.config;

import com.connect.dto.requests.LoginRequest;
import com.connect.dto.requests.RegisterRequest;
import com.connect.dto.response.LoginResponse;
import com.connect.dto.response.RegisterResponse;
import com.connect.entity.User;
import com.connect.repository.UserRepository;
import com.connect.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Optional<User> user = userRepository.findByName(request.getName());
        if (user.isPresent()) {
           return ResponseEntity
                   .badRequest()
                   .body("User already exists");
        }
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(newUser);
        RegisterResponse response =
                new RegisterResponse(newUser.getName(),
                        newUser.getEmail(),"User registered successfully");

        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> user = userRepository.findByName(request.getName());

        if(user.isPresent() &&
                passwordEncoder.matches(request.getPassword(), user.get().getPassword())){

            String token = jwtUtil.generateToken(user.get().getName());

            LoginResponse response =
                    new LoginResponse(user.get().getName(),user.get().getPassword(),token);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
}
