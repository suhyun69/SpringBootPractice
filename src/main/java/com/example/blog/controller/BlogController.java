package com.example.blog.controller;

import com.example.blog.domain.Article;
import com.example.blog.dto.AddArticleRequest;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blog")
@Tag(name = "Blog", description = "Blog API Document")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/article")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Article 작성", description = "Article을 작성합니다.")
    public ResponseEntity<Article> createArticle(@Valid @RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/articles")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Article 전체 조회", description = "전체 Article을 조회합니다.")
    public ResponseEntity<Page<Article>> getAllArticles(
            @RequestParam(value="pageNo", required = false, defaultValue= "0") String pageNo,
            @RequestParam(value="pageSize", required = false, defaultValue= "10") String pageSize) {

        return ResponseEntity.ok()
                .body(blogService.findAll(pageNo, pageSize));
    }

    @GetMapping("/articles/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Article 조회", description = "개별 Article을 조회합니다.")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
}
