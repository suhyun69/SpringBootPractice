package com.example.blog.controller;

import com.example.blog.config.jwt.TokenProvider;
import com.example.blog.domain.User;
import com.example.blog.dto.AddUserRequest;
import com.example.blog.dto.SignInRequest;
import com.example.blog.dto.SignInResponse;
import com.example.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API Document")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseEntity<Long> signup(@RequestBody AddUserRequest request) {

        Long userId = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userId);
    }

    @PostMapping("/signin")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "로그인", description = "로그인")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInRequest request) {

        try {
            // username, password 인증 시도
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            int a = 1;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("패스워드 불일치");
        }
        catch (InternalAuthenticationServiceException e) {
            throw new IllegalArgumentException("ID 없음");
        }

        User user = userService.findByEmail(request.getEmail());
        String token = tokenProvider.generateToken(user, Duration.ofDays(1));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignInResponse(token));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response, Principal principal) {

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok()
                .build();
    }
}
