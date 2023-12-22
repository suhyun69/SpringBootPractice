package com.example.blog.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ArticleNotFoundException.class })
    public ResponseEntity<?> handleArticleNotFoundException(ArticleNotFoundException articleNotFoundException, WebRequest request) {

        return super.handleExceptionInternal(
                articleNotFoundException,
                articleNotFoundException.getMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException, WebRequest request) {

        return super.handleExceptionInternal(
                runtimeException,
                runtimeException.getMessage(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

}
