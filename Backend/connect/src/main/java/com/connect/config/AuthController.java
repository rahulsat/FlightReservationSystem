package com.connect.config;

import com.connect.dto.requests.AdminLoginRequest;
import com.connect.dto.requests.LoginRequest;
import com.connect.dto.requests.RegisterRequest;
import com.connect.dto.response.AdminLoginResponse;
import com.connect.dto.response.LoginResponse;
import com.connect.dto.response.RegisterResponse;
import com.connect.entity.User;
import com.connect.repository.UserRepository;
import com.connect.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminConfig adminConfig;

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

        if(request == null){
            return ResponseEntity.badRequest().body("Request body cannot be empty");
        }

        if(request.getName() == null || request.getName().trim().isEmpty() ||
                request.getPassword() == null || request.getPassword().trim().isEmpty()){

            return ResponseEntity.badRequest()
                    .body("Username or password cannot be empty");
        }

        String username = request.getName().trim();
        String password = request.getPassword().trim();

        // 1️⃣ Check ADMIN
        if(adminConfig.getName().equals(username) &&
                adminConfig.getPassword().equals(password)){

            String role = "ADMIN";
            String token = jwtUtil.generateToken(username);

            LoginResponse response = new LoginResponse(
                    username,
                    role,
                    token
            );

            return ResponseEntity.ok(response);
        }

        // 2️⃣ Check USER from database
        Optional<User> user = userRepository.findByName(username);

        if(user.isPresent() &&
                passwordEncoder.matches(password, user.get().getPassword())){

            String role = "USER";
            String token = jwtUtil.generateToken(username);

            LoginResponse response = new LoginResponse(
                    user.get().getName(),
                    role,
                    token
            );

            return ResponseEntity.ok(response);
        }

        // 3️⃣ Invalid login
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
}
