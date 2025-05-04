package com.pragma.user_service.application.handler;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
import com.pragma.user_service.application.dto.request.UserClientRequestDto;
import com.pragma.user_service.application.dto.request.UserEmployeeRequestDto;
import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.domain.model.Auth;

public interface IUserHandler {

    void saveOwner(UserRequestDto userRequestDto);
    boolean isOwner(Long userId);
    Auth login(LoginRequestDto loginRequestDto);
    void saveEmployee(UserEmployeeRequestDto userEmployeeRequestDto);
    void saveClient(UserClientRequestDto userClientRequestDto);
    Long getIdRestaurantByIdEmployee();
    String getNumberPhoneByIdClient(Long idClient);
}
