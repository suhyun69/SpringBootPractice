package com.example.blog.controller;

import com.example.blog.dto.AddUserRequest;
import com.example.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API Document")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseEntity<Long> signup(@RequestBody AddUserRequest request) {

        Long userId = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userId);
    }
}
