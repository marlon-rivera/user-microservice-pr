package com.pragma.user_service.infrastructure.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationExceptionResponse(List<String> errors, String status, LocalDateTime timestamp) {
}