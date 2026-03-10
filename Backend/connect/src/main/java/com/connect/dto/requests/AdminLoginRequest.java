package com.connect.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequest {
    private String name;
    private String password;
}
