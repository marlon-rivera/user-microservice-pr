package com.pragma.user_service.application.handler.impl;

import com.pragma.user_service.application.dto.request.LoginRequestDto;
import com.pragma.user_service.application.dto.request.UserClientRequestDto;
import com.pragma.user_service.application.dto.request.UserEmployeeRequestDto;
import com.pragma.user_service.application.dto.request.UserRequestDto;
import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.application.mapper.IUserRequestMapper;
import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.model.Auth;
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

    @Override
    public boolean isOwner(Long userId) {
        return userServicePort.isOwner(userId);
    }
  
    @Override
    public Auth login(LoginRequestDto loginRequestDto) {
        return userServicePort.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @Override
    public void saveEmployee(UserEmployeeRequestDto userEmployeeRequestDto) {
        userServicePort.saveEmployee(userRequestMapper.toUser(userEmployeeRequestDto), userEmployeeRequestDto.getRestaurantId());
    }

    @Override
    public void saveClient(UserClientRequestDto userClientRequestDto) {
        userServicePort.saveClient(userRequestMapper.toUser(userClientRequestDto));
    }

    @Override
    public Long getIdRestaurantByIdEmployee() {
        return userServicePort.getIdRestaurantByIdEmployee();
    }

    @Override
    public String getNumberPhoneByIdClient(Long idClient) {
        return userServicePort.getPhoneNumberByIdClient(idClient);
    }
}
