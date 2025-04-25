package com.pragma.user_service.infrastructure.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ExceptionResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;


}
