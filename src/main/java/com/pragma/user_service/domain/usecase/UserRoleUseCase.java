package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRoleUseCase implements IUserRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    @Override
    public UserRole getRoleByName(String name) {
        return rolePersistencePort.getRoleByName(name);
    }
}
