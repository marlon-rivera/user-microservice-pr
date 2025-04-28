package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.exception.InvalidDataException;
import com.pragma.user_service.domain.model.Auth;
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
        Exception exception = assertThrows(InvalidDataException.class, () -> userUseCase.saveOwner(user));
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
        Exception exception = assertThrows(InvalidDataException.class, () -> userUseCase.saveOwner(user));
        assertEquals(UserValidationConstants.DNI_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userRoleServicePort, never()).getRoleByName(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void login_withValidCredentials_shouldReturnAuth() {
        // Arrange
        String email = "juan@example.com";
        String password = "Password123*%";
        Auth expectedAuth = new Auth("token");

        when(userPersistencePort.authenticateUser(email, password)).thenReturn(expectedAuth);

        // Act
        Auth result = userUseCase.login(email, password);

        // Assert
        assertEquals(expectedAuth, result);
        verify(userPersistencePort).authenticateUser(email, password);
    }

    @Test
    void login_withInvalidCredentials_shouldThrowException() {
        // Arrange
        String email = "juan@example.com";
        String password = "wrongPassword";

        when(userPersistencePort.authenticateUser(email, password)).thenReturn(null);

        // Act & Assert
        Exception exception = assertThrows(InvalidDataException.class,
                () -> userUseCase.login(email, password));
        assertEquals(UserValidationConstants.INVALID_CREDENTIALS, exception.getMessage());
        verify(userPersistencePort).authenticateUser(email, password);
    }

}