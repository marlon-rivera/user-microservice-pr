package com.pragma.user_service.infrastructure.input.rest;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
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

}