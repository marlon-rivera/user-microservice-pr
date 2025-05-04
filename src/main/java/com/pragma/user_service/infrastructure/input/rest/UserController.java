package com.pragma.user_service.infrastructure.input.rest;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
import com.pragma.user_service.application.dto.request.UserClientRequestDto;
import com.pragma.user_service.application.dto.request.UserEmployeeRequestDto;
import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.dto.utils.constants.ResponsesCodes;
import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.domain.model.Auth;
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
      
    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_LOGIN_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_LOGIN_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_LOGIN_RESPONSE_200_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_LOGIN_RESPONSE_400_DESCRIPTION
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Auth> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Auth auth = userHandler.login(loginRequestDto);
        return ResponseEntity.ok(auth);
    }

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_EMPLOYEE_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_EMPLOYEE_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.CREATED,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_201_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_400_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.CONFLICT,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_409_DESCRIPTION
                    )
            }
    )
    @PostMapping("/employee")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody UserEmployeeRequestDto userEmployeeRequestDto) {
        userHandler.saveEmployee(userEmployeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_CLIENT_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_CLIENT_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.CREATED,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_CLIENT_RESPONSE_201_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_CLIENT_RESPONSE_400_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.CONFLICT,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_SAVE_CLIENT_RESPONSE_409_DESCRIPTION
                    )
            }
    )
    @PostMapping("/client")
    public ResponseEntity<Void> createClient(@Valid @RequestBody UserClientRequestDto userClientRequestDto) {
        userHandler.saveClient(userClientRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_RESPONSE_200_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.BAD_REQUEST,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_RESPONSE_400_DESCRIPTION
                    )
            }
    )
    @GetMapping("/restaurant/employee")
    public ResponseEntity<Long> getIdRestaurantByIdEmployee() {
        Long idRestaurant = userHandler.getIdRestaurantByIdEmployee();
        return ResponseEntity.ok(idRestaurant);
    }

    @Operation(
            summary = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_SUMMARY,
            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = ResponsesCodes.OK,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_RESPONSE_200_DESCRIPTION
                    ),
                    @ApiResponse(
                            responseCode = ResponsesCodes.NOT_FOUND,
                            description = UserControllerConstantsOpenApi.USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_RESPONSE_404_DESCRIPTION
                    )
            }
    )
    @GetMapping("/client-phone/{id}")
    public ResponseEntity<String> getPhoneNumberByIdClient(@PathVariable Long id) {
        String phoneNumber = userHandler.getNumberPhoneByIdClient(id);
        return ResponseEntity.ok(phoneNumber);
    }
}
