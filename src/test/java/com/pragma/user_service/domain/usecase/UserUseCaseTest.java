package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.exception.ResourceConflictException;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.domain.util.constants.UserUseCaseConstants;
import com.pragma.user_service.domain.util.constants.UserValidationConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IUserRoleServicePort userRoleServicePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;
    private UserRole userRole;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Juan");
        user.setLastName("Pérez");
        user.setDni("1234567890");
        user.setPhoneNumber("+573001234567");
        user.setEmail("juan@example.com");
        user.setPassword("Password123*%");
        user.setDateOfBirth(LocalDate.of(2003, 5, 6));

        userRole = new UserRole();
        userRole.setId(1L);
        userRole.setName(UserUseCaseConstants.ROLE_OWNER);
    }

    @Test
    void saveOwner_withValidData_shouldSaveUser() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(false);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_OWNER)).thenReturn(userRole);
        doNothing().when(userPersistencePort).saveUser(any(User.class));

        // Act
        assertDoesNotThrow(() -> userUseCase.saveOwner(user));

        // Assert
        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userRoleServicePort).getRoleByName(UserUseCaseConstants.ROLE_OWNER);
        verify(userPersistencePort).saveUser(user);
        assertEquals(userRole, user.getRole());
        assertNotEquals("Password123", user.getPassword(), "La contraseña debe ser encriptada");
    }

    @Test
    void saveOwner_withExistingEmail_shouldThrowException() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveOwner(user));
        assertEquals(UserValidationConstants.EMAIL_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort, never()).existsByDni(anyString());
        verify(userRoleServicePort, never()).getRoleByName(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void saveOwner_withExistingDni_shouldThrowException() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveOwner(user));
        assertEquals(UserValidationConstants.DNI_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userRoleServicePort, never()).getRoleByName(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void isOwner_withOwnerUser_shouldReturnTrue() {
        // Arrange
        Long userId = 1L;
        User owner = new User();
        owner.setRole(userRole); // userRole ya está configurado como ROLE_OWNER en setUp

        when(userPersistencePort.findById(userId)).thenReturn(Optional.of(owner));

        // Act
        boolean result = userUseCase.isOwner(userId);

        // Assert
        assertTrue(result);
        verify(userPersistencePort).findById(userId);
    }

    @Test
    void isOwner_withNonOwnerUser_shouldReturnFalse() {
        // Arrange
        Long userId = 1L;
        User nonOwnerUser = new User();
        UserRole clientRole = new UserRole();
        clientRole.setId(2L);
        clientRole.setName("CLIENT");
        nonOwnerUser.setRole(clientRole);

        when(userPersistencePort.findById(userId)).thenReturn(Optional.of(nonOwnerUser));

        // Act
        boolean result = userUseCase.isOwner(userId);

        // Assert
        assertFalse(result);
        verify(userPersistencePort).findById(userId);
    }

    @Test
    void isOwner_withNonExistentUser_shouldReturnFalse() {
        // Arrange
        Long userId = 999L;
        when(userPersistencePort.findById(userId)).thenReturn(Optional.empty());

        // Act
        boolean result = userUseCase.isOwner(userId);

        // Assert
        assertFalse(result);
        verify(userPersistencePort).findById(userId);
    }

}