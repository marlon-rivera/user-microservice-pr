package com.pragma.user_service.application.handler.impl;

import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.application.mapper.IUserRequestMapper;
import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.model.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toUser(userRequestDto);
        userServicePort.saveOwner(user);
    }
}
