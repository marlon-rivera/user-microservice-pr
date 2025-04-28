package com.pragma.user_service.infrastructure.security.jwt;

import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.user_service.infrastructure.security.constants.JWTConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException(JWTConstants.USER_NOT_FOUND_EMAIL));
        GrantedAuthority authority = new SimpleGrantedAuthority(JWTConstants.ROLE_SECURITY_PREFIX + user.getRole().getName());
        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                List.of(authority)
        );
    }

}
