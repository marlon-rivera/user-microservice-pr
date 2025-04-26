package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}