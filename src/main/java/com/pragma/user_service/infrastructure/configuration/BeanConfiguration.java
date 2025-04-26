package com.pragma.user_service.infrastructure.configuration;

import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.application.handler.impl.UserHandlerImpl;
import com.pragma.user_service.application.mapper.IUserRequestMapper;
import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.spi.IRolePersistencePort;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.domain.usecase.UserUseCase;
import com.pragma.user_service.infrastructure.out.jpa.adapter.UserAdapterJPA;
import com.pragma.user_service.infrastructure.out.jpa.adapter.UserRoleAdapterJPA;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserRoleEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRoleEntityMapper userRoleEntityMapper;
    private final IUserRequestMapper userRequestMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapterJPA(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new UserRoleAdapterJPA(userRoleRepository, userRoleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort());
    }

    @Bean
    public IUserHandler userHandler(){
        return new UserHandlerImpl(userServicePort(), userRequestMapper);
    }

}
