package com.pragma.user_service.infrastructure.security;

import com.pragma.user_service.domain.model.RoleEnum;
import com.pragma.user_service.infrastructure.security.jwt.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers(HttpMethod.GET, "/users/restaurant/employee").hasRole(RoleEnum.EMPLOYEE.name())
                                        .requestMatchers("/users").hasRole(RoleEnum.ADMIN.name())
                                        .requestMatchers("/users/employee").hasRole(RoleEnum.OWNER.name())
                                        .requestMatchers("/users/login").permitAll()
                                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                        .anyRequest().permitAll())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthFilter,
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

}
