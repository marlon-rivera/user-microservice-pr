package com.pragma.user_service.infrastructure.jwt;

import com.pragma.user_service.domain.spi.IJWTPort;
import com.pragma.user_service.infrastructure.security.jwt.JWTService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAdapter implements IJWTPort {

    private final JWTService jwtService;

    @Override
    public String getToken(String username, String role) {
        return jwtService.getToken(username, role);
    }
}
