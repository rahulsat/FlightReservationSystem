package com.connect.controller;

import com.connect.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> request){

        String username = request.get("username");
        String password = request.get("password");

        if(username.equals("admin") && password.equals("admin123")){
            return jwtUtil.generateToken(username);
        }

        return "Invalid credentials";
    }
}