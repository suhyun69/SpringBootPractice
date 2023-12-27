package com.example.blog.service;

import com.example.blog.dto.AddUserRequest;

public interface UserService {
    Long save(AddUserRequest dto);
}
