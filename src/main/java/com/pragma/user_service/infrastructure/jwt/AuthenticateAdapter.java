package com.pragma.user_service.infrastructure.jwt;

import com.pragma.user_service.domain.spi.IAuthenticatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@RequiredArgsConstructor
public class AuthenticateAdapter implements IAuthenticatePort {

    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticateUser(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            User userAuth = (User) auth.getPrincipal();
            return Long.parseLong(userAuth.getUsername());
        }
        return null;
    }
}
