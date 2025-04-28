package com.pragma.user_service.application.handler;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.domain.model.Auth;

public interface IUserHandler {

    void saveOwner(UserRequestDto userRequestDto);
    Auth login(LoginRequestDto loginRequestDto);
}
