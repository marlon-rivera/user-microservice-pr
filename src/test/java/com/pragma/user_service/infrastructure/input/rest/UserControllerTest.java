package com.pragma.user_service.infrastructure.input.rest;

import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.handler.IUserHandler;
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

}