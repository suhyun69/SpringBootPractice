package com.example.blog.service.impl;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public Page<Article> findAll(String pageNo, String pageSize) {
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        return blogRepository.findAll(pageRequest);
    }
}
