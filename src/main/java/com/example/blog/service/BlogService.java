package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Article save(AddArticleRequest request);
    Page<Article> findAll(String pageNo, String pageSize);
}
