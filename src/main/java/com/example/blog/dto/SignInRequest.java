package com.example.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignInRequest {
    private String email;
    private String password;
}