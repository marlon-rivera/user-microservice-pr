package com.pragma.user_service.infrastructure.exceptionhandler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class JWTControllerAdvisor {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now())
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString(), LocalDateTime.now())
        );
    }

}
