package com.connect.service;

import com.connect.entity.User;
import com.connect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<User> patchUser(int id, User updatedUser) {

        return userRepository.findById(id).map(user -> {

            if(updatedUser.getName() != null){
                user.setName(updatedUser.getName());
            }

            if(updatedUser.getEmail() != null){
                user.setEmail(updatedUser.getEmail());
            }

            if(updatedUser.getAge() != null){
                user.setAge(updatedUser.getAge());
            }

            if(updatedUser.getPhoneNumber() != null){
                user.setPhoneNumber(updatedUser.getPhoneNumber());
            }

            if(updatedUser.getPassword() != null){
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            return ResponseEntity.ok(userRepository.save(user));

        }).orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<User> putUser(int id, User updatedUser) {

        return userRepository.findById(id).map(user -> {

            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setAge(updatedUser.getAge());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

            return ResponseEntity.ok(userRepository.save(user));

        }).orElse(ResponseEntity.notFound().build());
    }
}
