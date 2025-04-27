package com.pragma.user_service.infrastructure.input.rest;

import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.dto.utils.constants.ResponsesCodes;
import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.infrastructure.util.constants.openapi.UserControllerConstantsOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_DESCRIPTION,
            tags = {UserControllerConstantsOpenApi.USER_CONTROLLER_TAG},
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.CREATED,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_RESPONSE_201_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_RESPONSE_400_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.CONFLICT,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_RESPONSE_409_DESCRIPTION
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> createOwner(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_IS_OWNER_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_IS_OWNER_DESCRIPTION,
            tags = {UserControllerConstantsOpenApi.USER_CONTROLLER_TAG},
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_IS_OWNER_RESPONSE_200_DESCRIPTION
                    )
            }
    )
    @GetMapping("/owner/{id}")
    public ResponseEntity<Boolean> isOwner(@PathVariable Long id) {
        return ResponseEntity.ok(userHandler.isOwner(id));
    }
}
