package com.connect.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminLoginResponse {
    private String name;
    private String message;
    private String role;
    private String token;
}
