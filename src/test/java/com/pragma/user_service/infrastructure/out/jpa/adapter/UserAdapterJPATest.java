package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.spi.IAuthenticatePort;
import com.pragma.user_service.domain.spi.IJWTPort;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserRoleEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAdapterJPATest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserAdapterJPA userAdapterJPA;

    @Mock
    private IJWTPort jwtPort;

    @Mock
    private IAuthenticatePort authenticatePort;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setDni("12345678");

        userEntity = new UserEntity();
        userEntity.setEmail("test@example.com");
        userEntity.setDni("12345678");
    }

    @Test
    void saveUser_ShouldSaveUserCorrectly() {
        when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);

        userAdapterJPA.saveUser(user);

        verify(userEntityMapper).toUserEntity(user);
        verify(userRepository).save(userEntity);
    }

    @Test
    void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userAdapterJPA.existsByEmail(email);

        assertTrue(result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByEmail_ShouldReturnFalse_WhenEmailDoesNotExist() {
        String email = "nonexistent@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        boolean result = userAdapterJPA.existsByEmail(email);

        assertFalse(result);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void existsByDni_ShouldReturnTrue_WhenDniExists() {
        String dni = "12345678";
        when(userRepository.existsByDni(dni)).thenReturn(true);

        boolean result = userAdapterJPA.existsByDni(dni);

        assertTrue(result);
        verify(userRepository).existsByDni(dni);
    }

    @Test
    void existsByDni_ShouldReturnFalse_WhenDniDoesNotExist() {
        String dni = "87654321";
        when(userRepository.existsByDni(dni)).thenReturn(false);

        boolean result = userAdapterJPA.existsByDni(dni);

        assertFalse(result);
        verify(userRepository).existsByDni(dni);
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(userEntity));
        when(userEntityMapper.toOptionalUser(java.util.Optional.of(userEntity))).thenReturn(java.util.Optional.of(user));

        java.util.Optional<User> result = userAdapterJPA.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository).findById(userId);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenUserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        java.util.Optional<User> result = userAdapterJPA.findById(userId);

        assertFalse(result.isPresent());
        verify(userRepository).findById(userId);
    }

    @Test
    void authenticateUser_ShouldReturnAuth_WhenCredentialsAreValid() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String userId = "1";
        String roleName = "USER";
        String token = "jwt-token";

        userEntity.setId(1L);
        userEntity.setRole(new UserRoleEntity(1L, roleName, null));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(jwtPort.getToken(userId, roleName)).thenReturn(token);

        // Act
        Auth result = userAdapterJPA.authenticateUser(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(token, result.getToken());
        verify(userRepository).findByEmail(email);
        verify(authenticatePort).authenticateUser(userId, password);
        verify(jwtPort).getToken(userId, roleName);
    }

    @Test
    void authenticateUser_ShouldReturnNull_WhenUserDoesNotExist() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        Auth result = userAdapterJPA.authenticateUser(email, password);

        // Assert
        assertNull(result);
        verify(userRepository).findByEmail(email);
    }
}