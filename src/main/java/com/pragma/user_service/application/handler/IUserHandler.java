package com.pragma.user_service.application.handler;

import com.pragma.user_service.application.dto.request.UserRequestDto;

public interface IUserHandler {

    void saveOwner(UserRequestDto userRequestDto);
    boolean isOwner(Long userId);

}
