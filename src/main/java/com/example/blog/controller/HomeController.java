package com.example.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Tag(name = "Template", description = "템플릿 API Document")
public class HomeController {

    @GetMapping("/hello")
    @Operation(summary = "템플릿 리스트", description = "템플릿의 모든 리스트를 조회합니다.")
    public String test() {
        return "Hello, stranger.";
    }
}
