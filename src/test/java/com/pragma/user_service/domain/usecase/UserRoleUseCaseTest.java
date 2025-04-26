package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    private UserRoleUseCase userRoleUseCase;

    @BeforeEach
    void setUp() {
        userRoleUseCase = new UserRoleUseCase(rolePersistencePort);
    }

    @Test
    void getRoleByName_ShouldReturnUserRole_WhenRoleExists() {
        // Arrange
        String roleName = "OWNER";
        UserRole expectedRole = new UserRole(1L, roleName);
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(expectedRole);

        // Act
        UserRole result = userRoleUseCase.getRoleByName(roleName);

        // Assert
        assertEquals(expectedRole, result);
        verify(rolePersistencePort, times(1)).getRoleByName(roleName);
    }

    @Test
    void getRoleByName_ShouldReturnNull_WhenRoleDoesNotExist() {
        // Arrange
        String roleName = "NONEXISTENT_ROLE";
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(null);

        // Act
        UserRole result = userRoleUseCase.getRoleByName(roleName);

        // Assert
        assertNull(result);
        verify(rolePersistencePort, times(1)).getRoleByName(roleName);
    }

}