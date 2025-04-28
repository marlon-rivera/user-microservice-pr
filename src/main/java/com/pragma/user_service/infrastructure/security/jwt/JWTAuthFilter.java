package com.pragma.user_service.infrastructure.security.jwt;

import com.pragma.user_service.infrastructure.security.constants.JWTConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                FilterChain filterChain) throws IOException, ServletException {
        final String token = getTokenFromRequest(request);
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtService.getSubjectFromToken(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            authenticate(username, token, request);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(String id, String token, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        if(jwtService.isTokenValid(token, userDetails)){
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith(JWTConstants.BEARER_PREFIX)) {
            return authHeader.substring(JWTConstants.BEARER_PREFIX.length());
        }
        return null;
    }

}
