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
    @PostMapping("/AdminLogin")
    public ResponseEntity<?> adminLogin(@RequestBody AdminLoginRequest request){
        if(request == null){
            return ResponseEntity
                    .badRequest()
                    .body("Request body cannot be empty");
        }

        // 2️⃣ Empty username or password
        if(request.getName() == null || request.getName().trim().isEmpty() ||
                request.getPassword() == null || request.getPassword().trim().isEmpty()){

            return ResponseEntity
                    .badRequest()
                    .body("Username or password cannot be empty");
        }

        // 3️⃣ Trim spaces
        String username = request.getName().trim();
        String password = request.getPassword().trim();
        if(adminConfig.getName().equals(request.getName()) &&
                adminConfig.getPassword().equals(request.getPassword())){
            String role="ADMIN";
            String token=jwtUtil.generateToken(role);

            AdminLoginResponse response=new AdminLoginResponse(request.getName(),
                    "Successfully Logged in as Admin",role,token);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok("Sorry you dont have access");
    }
}
