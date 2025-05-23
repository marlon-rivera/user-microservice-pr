package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.exception.ResourceConflictException;
import com.pragma.user_service.domain.exception.InvalidDataException;
import com.pragma.user_service.domain.model.*;
import com.pragma.user_service.domain.spi.IAuthenticatePort;
import com.pragma.user_service.domain.spi.IEmployeeRestaurantPersistencePort;
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

    @Mock
    private IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;

    @Mock
    private IAuthenticatePort authenticatePort;

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
        user.setRole(userRole);
    }

    @Test
    void saveOwner_withValidData_shouldSaveUser() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(false);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_OWNER)).thenReturn(userRole);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(user);
        user.setRole(userRole);

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
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_OWNER)).thenReturn(userRole);
        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveOwner(user));
        assertEquals(UserValidationConstants.EMAIL_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort, never()).existsByDni(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void saveOwner_withExistingDni_shouldThrowException() {
        // Arrange
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(true);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_OWNER)).thenReturn(userRole);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveOwner(user));

        assertEquals(UserValidationConstants.DNI_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
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

    @Test
    void saveEmployee_withValidData_shouldSaveUserAndEmployeeRestaurant() {
        // Arrange
        Long restaurantId = 1L;
        UserRole employeeRole = new UserRole();
        employeeRole.setId(3L);
        employeeRole.setName(UserUseCaseConstants.ROLE_EMPLOYEE);

        User savedUser = user;
        savedUser.setId(1L);
        savedUser.setRole(employeeRole);

        when(userPersistencePort.validateOwnerRestaurant(restaurantId)).thenReturn(true);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_EMPLOYEE)).thenReturn(employeeRole);
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(false);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(savedUser);
        doNothing().when(employeeRestaurantPersistencePort).saveEmployeeRestaurant(any(EmployeeRestaurant.class));

        // Act
        userUseCase.saveEmployee(user, restaurantId);

        // Assert
        verify(userPersistencePort).validateOwnerRestaurant(restaurantId);
        verify(userRoleServicePort).getRoleByName(UserUseCaseConstants.ROLE_EMPLOYEE);
        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userPersistencePort).saveUser(user);
        verify(employeeRestaurantPersistencePort).saveEmployeeRestaurant(any(EmployeeRestaurant.class));
    }

    @Test
    void saveEmployee_withInvalidRestaurantOwner_shouldThrowException() {
        // Arrange
        Long restaurantId = 1L;
        when(userPersistencePort.validateOwnerRestaurant(restaurantId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(InvalidDataException.class,
                () -> userUseCase.saveEmployee(user, restaurantId));

        assertEquals(UserUseCaseConstants.INVALID_OWNER_RESTAURANT, exception.getMessage());
        verify(userPersistencePort).validateOwnerRestaurant(restaurantId);
        verify(userRoleServicePort, never()).getRoleByName(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
        verify(employeeRestaurantPersistencePort, never()).saveEmployeeRestaurant(any(EmployeeRestaurant.class));
    }

    @Test
    void saveEmployee_withExistingEmail_shouldThrowException() {
        // Arrange
        Long restaurantId = 1L;
        when(userPersistencePort.validateOwnerRestaurant(restaurantId)).thenReturn(true);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_EMPLOYEE)).thenReturn(new UserRole(1L, UserUseCaseConstants.ROLE_EMPLOYEE));
        when(userPersistencePort.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class,
                () -> userUseCase.saveEmployee(user, restaurantId));

        assertEquals(UserValidationConstants.EMAIL_ALREADY_EXISTS, exception.getMessage());
        verify(userPersistencePort).validateOwnerRestaurant(restaurantId);
        verify(userRoleServicePort).getRoleByName(UserUseCaseConstants.ROLE_EMPLOYEE);
        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort, never()).saveUser(any(User.class));
        verify(employeeRestaurantPersistencePort, never()).saveEmployeeRestaurant(any(EmployeeRestaurant.class));
    }

    @Test
    void saveClient_withValidData_shouldSaveUser() {
        // Arrange
        UserRole clientRole = new UserRole();
        clientRole.setId(4L);
        clientRole.setName(UserUseCaseConstants.ROLE_CLIENT);

        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(false);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_CLIENT)).thenReturn(clientRole);
        when(userPersistencePort.saveUser(any(User.class))).thenReturn(user);

        // Act
        assertDoesNotThrow(() -> userUseCase.saveClient(user));

        // Assert
        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userRoleServicePort).getRoleByName(UserUseCaseConstants.ROLE_CLIENT);
        verify(userPersistencePort).saveUser(user);
        assertEquals(clientRole, user.getRole());
        assertNotEquals("Password123*%", user.getPassword(), "La contraseña debe ser encriptada");
    }

    @Test
    void saveClient_withExistingEmail_shouldThrowException() {
        // Arrange
        UserRole clientRole = new UserRole();
        clientRole.setId(4L);
        clientRole.setName(UserUseCaseConstants.ROLE_CLIENT);

        when(userPersistencePort.existsByEmail(anyString())).thenReturn(true);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_CLIENT)).thenReturn(clientRole);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveClient(user));
        assertEquals(UserValidationConstants.EMAIL_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort, never()).existsByDni(anyString());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void saveClient_withExistingDni_shouldThrowException() {
        // Arrange
        UserRole clientRole = new UserRole();
        clientRole.setId(4L);
        clientRole.setName(UserUseCaseConstants.ROLE_CLIENT);

        when(userPersistencePort.existsByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.existsByDni(anyString())).thenReturn(true);
        when(userRoleServicePort.getRoleByName(UserUseCaseConstants.ROLE_CLIENT)).thenReturn(clientRole);

        // Act & Assert
        Exception exception = assertThrows(ResourceConflictException.class, () -> userUseCase.saveClient(user));
        assertEquals(UserValidationConstants.DNI_ALREADY_EXISTS, exception.getMessage());

        verify(userPersistencePort).existsByEmail(user.getEmail());
        verify(userPersistencePort).existsByDni(user.getDni());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void getIdRestaurantByIdEmployee_whenEmployeeFound_shouldReturnRestaurantId() {
        // Arrange
        Long employeeId = 1L;
        Long expectedRestaurantId = 2L;

        EmployeeRestaurantId employeeRestaurantId = new EmployeeRestaurantId(employeeId, expectedRestaurantId);
        EmployeeRestaurant employeeRestaurant = new EmployeeRestaurant(employeeRestaurantId);

        when(authenticatePort.getCurrentUserId()).thenReturn(employeeId);
        when(employeeRestaurantPersistencePort.getEmployeeRestaurantByIdEmployee(employeeId))
                .thenReturn(Optional.of(employeeRestaurant));

        // Act
        Long result = userUseCase.getIdRestaurantByIdEmployee();

        // Assert
        assertEquals(expectedRestaurantId, result);
        verify(authenticatePort).getCurrentUserId();
        verify(employeeRestaurantPersistencePort).getEmployeeRestaurantByIdEmployee(employeeId);
    }

    @Test
    void getIdRestaurantByIdEmployee_whenEmployeeNotFound_shouldThrowException() {
        // Arrange
        Long employeeId = 1L;

        when(authenticatePort.getCurrentUserId()).thenReturn(employeeId);
        when(employeeRestaurantPersistencePort.getEmployeeRestaurantByIdEmployee(employeeId))
                .thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(InvalidDataException.class,
                () -> userUseCase.getIdRestaurantByIdEmployee());

        assertEquals(UserUseCaseConstants.EMPLOYEE_RESTAURANT_NOT_FOUND, exception.getMessage());
        verify(authenticatePort).getCurrentUserId();
        verify(employeeRestaurantPersistencePort).getEmployeeRestaurantByIdEmployee(employeeId);
    }

    @Test
    void getPhoneNumberByIdClient_whenClientExists_shouldReturnPhoneNumber() {
        // Arrange
        Long clientId = 1L;
        String expectedPhoneNumber = "+573001234567";

        User client = new User();
        client.setId(clientId);
        client.setPhoneNumber(expectedPhoneNumber);

        when(userPersistencePort.findById(clientId)).thenReturn(Optional.of(client));

        // Act
        String result = userUseCase.getPhoneNumberByIdClient(clientId);

        // Assert
        assertEquals(expectedPhoneNumber, result);
        verify(userPersistencePort).findById(clientId);
    }

    @Test
    void getPhoneNumberByIdClient_whenClientNotFound_shouldThrowException() {
        // Arrange
        Long nonExistentClientId = 999L;

        when(userPersistencePort.findById(nonExistentClientId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(InvalidDataException.class,
                () -> userUseCase.getPhoneNumberByIdClient(nonExistentClientId));

        assertEquals(UserUseCaseConstants.CLIENT_NOT_FOUND, exception.getMessage());
        verify(userPersistencePort).findById(nonExistentClientId);
    }
}