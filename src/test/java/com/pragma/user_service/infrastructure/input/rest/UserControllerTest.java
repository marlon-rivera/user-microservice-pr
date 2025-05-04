package com.pragma.user_service.infrastructure.input.rest;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
import com.pragma.user_service.application.dto.request.UserClientRequestDto;
import com.pragma.user_service.application.dto.request.UserEmployeeRequestDto;
import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.domain.model.Auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOwner_ShouldReturnCreatedStatus() {
        UserRequestDto userRequestDto = new UserRequestDto();
        doNothing().when(userHandler).saveOwner(userRequestDto);

        ResponseEntity<Void> response = userController.createOwner(userRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userHandler).saveOwner(userRequestDto);
    }

    @Test
    void isOwner_ShouldReturnOkStatusWithResult() {
        Long userId = 1L;
        boolean isOwnerResult = true;
        when(userHandler.isOwner(userId)).thenReturn(isOwnerResult);

        ResponseEntity<Boolean> response = userController.isOwner(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(isOwnerResult, response.getBody());
        verify(userHandler).isOwner(userId);
    }
  
    @Test
    void login_ShouldReturnOkStatusAndAuthObject() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        Auth expectedAuth = new Auth();
        when(userHandler.login(loginRequestDto)).thenReturn(expectedAuth);

        // Act
        ResponseEntity<Auth> response = userController.login(loginRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAuth, response.getBody());
        verify(userHandler).login(loginRequestDto);
    }

    @Test
    void createEmployee_ShouldReturnCreatedStatus() {
        UserEmployeeRequestDto userEmployeeRequestDto = new UserEmployeeRequestDto();
        doNothing().when(userHandler).saveEmployee(userEmployeeRequestDto);

        ResponseEntity<Void> response = userController.createEmployee(userEmployeeRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userHandler).saveEmployee(userEmployeeRequestDto);
    }

    @Test
    void createClient_ShouldReturnCreatedStatus() {
        UserClientRequestDto userClientRequestDto = new UserClientRequestDto();
        doNothing().when(userHandler).saveClient(userClientRequestDto);

        ResponseEntity<Void> response = userController.createClient(userClientRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
        verify(userHandler).saveClient(userClientRequestDto);
    }

    @Test
    void getIdRestaurantByIdEmployee_ShouldReturnOkStatusWithRestaurantId() {
        // Arrange
        Long expectedRestaurantId = 123L;
        when(userHandler.getIdRestaurantByIdEmployee()).thenReturn(expectedRestaurantId);

        // Act
        ResponseEntity<Long> response = userController.getIdRestaurantByIdEmployee();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRestaurantId, response.getBody());
        verify(userHandler).getIdRestaurantByIdEmployee();
    }

    @Test
    void getNumberPhoneByIdClient_ShouldReturnOkStatusWithPhoneNumber() {
        // Arrange
        Long clientId = 1L;
        String expectedPhoneNumber = "1234567890";
        when(userHandler.getNumberPhoneByIdClient(clientId)).thenReturn(expectedPhoneNumber);

        // Act
        ResponseEntity<String> response = userController.getPhoneNumberByIdClient(clientId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPhoneNumber, response.getBody());
        verify(userHandler).getNumberPhoneByIdClient(clientId);
    }
}