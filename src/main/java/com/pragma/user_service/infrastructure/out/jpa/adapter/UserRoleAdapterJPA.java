package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.domain.spi.IRolePersistencePort;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserRoleEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRoleAdapterJPA implements IRolePersistencePort {

    private final IUserRoleRepository userRoleRepository;
    private final IUserRoleEntityMapper userRoleEntityMapper;

    @Override
    public UserRole getRoleByName(String name) {
        return userRoleEntityMapper.toUserRole(userRoleRepository.findByName(name));

    }
}
