package com.connect.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConfig {
    @Value("${app.admin.name}")
    private String name;
    @Value("${app.admin.password}")
    private String password;
}
