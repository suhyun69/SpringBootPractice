package com.example.blog.service;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;

import java.util.List;

public interface BlogService {
    Article save(AddArticleRequest request);
    List<Article> findAll();
}
