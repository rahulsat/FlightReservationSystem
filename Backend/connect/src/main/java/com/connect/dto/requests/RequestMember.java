package com.connect.dto.requests;

import com.connect.entity.User;
import lombok.Data;

@Data
public class RequestMember {
    private String name;
    private int age;
    private String phoneNumber;
    private Integer userId; // important for mapping
}