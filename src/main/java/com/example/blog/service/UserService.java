package com.example.blog.service;

import com.example.blog.domain.User;
import com.example.blog.dto.AddUserRequest;

public interface UserService {
    Long save(AddUserRequest dto);
    User findByEmail(String email);
}
