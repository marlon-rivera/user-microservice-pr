package com.pragma.user_service.infrastructure.jwt;

import com.pragma.user_service.domain.spi.IAuthenticatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class AuthenticateAdapter implements IAuthenticatePort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticateUser(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
