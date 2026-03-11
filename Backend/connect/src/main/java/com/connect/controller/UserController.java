package com.connect.controller;

import com.connect.entity.User;
import com.connect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable int id,@RequestBody User request){
        return ResponseEntity.ok(userService.patchUser(id,request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable int id,
            @RequestBody User request) {

        return userService.putUser(id, request);
    }
}
