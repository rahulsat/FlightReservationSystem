package com.connect.config;

import com.connect.dto.requests.LoginRequest;
import com.connect.dto.requests.RegisterRequest;
import com.connect.dto.response.LoginResponse;
import com.connect.dto.response.RegisterResponse;
import com.connect.entity.User;
import com.connect.repository.UserRepository;
import com.connect.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // ✅ Ensure CORS is handled
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminConfig adminConfig;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Check if user exists by email instead of just name
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .build();
        userRepository.save(newUser);

        return ResponseEntity.ok(new RegisterResponse(newUser.getName(), newUser.getEmail(), "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request == null) return ResponseEntity.badRequest().body("Request body empty");

        // ✅ We treat the 'name' field in LoginRequest as 'identifier' (could be name OR email)
        String identifier = (request.getName() != null) ? request.getName().trim() : "";
        String password = (request.getPassword() != null) ? request.getPassword().trim() : "";

        if (identifier.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Identifier or password cannot be empty");
        }

        // 1️⃣ Check ADMIN (Comparing identifier against admin name/email)
        if (adminConfig.getName().equals(identifier) && adminConfig.getPassword().equals(password)) {
            String token = jwtUtil.generateToken(identifier);
            return ResponseEntity.ok(new LoginResponse(identifier, "ADMIN", token));
        }

        // 2️⃣ Check USER from database (Try Email first, then Name)
        Optional<User> userOpt = userRepository.findByEmail(identifier);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByName(identifier);
        }

        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            User user = userOpt.get();
            String token = jwtUtil.generateToken(user.getEmail()); // Use email for token

            // ✅ CRITICAL: Return the EMAIL as the username so Navbar can use it
            LoginResponse response = new LoginResponse(
                    user.getEmail(),
                    "USER",
                    token
            );

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/username or password");
    }
}