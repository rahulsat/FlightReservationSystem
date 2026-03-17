package com.connect.dto.response;

import com.connect.entity.User;
import lombok.Data;

@Data
public class ResponseMember {
    private String name;
    private int age;
    private String phoneNumber;
    private Integer userId;
    private String userName;
}