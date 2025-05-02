package com.pragma.user_service.infrastructure.configuration;

import com.pragma.user_service.application.handler.IUserHandler;
import com.pragma.user_service.application.handler.impl.UserHandlerImpl;
import com.pragma.user_service.application.mapper.IUserRequestMapper;
import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.spi.*;
import com.pragma.user_service.domain.usecase.UserRoleUseCase;
import com.pragma.user_service.domain.usecase.UserUseCase;
import com.pragma.user_service.infrastructure.feign.adapter.RestaurantAdapterFeign;
import com.pragma.user_service.infrastructure.feign.client.IRestaurantFeignClient;
import com.pragma.user_service.infrastructure.jwt.AuthenticateAdapter;
import com.pragma.user_service.infrastructure.jwt.JwtAdapter;
import com.pragma.user_service.infrastructure.out.jpa.adapter.EmployeeRestaurantAdapterJPA;
import com.pragma.user_service.infrastructure.out.jpa.adapter.UserAdapterJPA;
import com.pragma.user_service.infrastructure.out.jpa.adapter.UserRoleAdapterJPA;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IEmployeeRestaurantEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserRoleEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IEmployeeRestaurantRepository;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRoleRepository;
import com.pragma.user_service.infrastructure.security.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserRoleRepository userRoleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRoleEntityMapper userRoleEntityMapper;
    private final IUserRequestMapper userRequestMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IRestaurantFeignClient restaurantFeignClient;
    private final IEmployeeRestaurantRepository employeeRestaurantRepository;
    private final IEmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;


    @Bean
    public IJWTPort jwtPort() {
        return new JwtAdapter(jwtService);
    }

    @Bean
    public IAuthenticatePort authenticatePort() {
        return new AuthenticateAdapter(authenticationManager);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantAdapterFeign(restaurantFeignClient);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapterJPA(userRepository, userEntityMapper, jwtPort(), authenticatePort(), restaurantPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new UserRoleAdapterJPA(userRoleRepository, userRoleEntityMapper);
    }

    @Bean
    public IUserRoleServicePort userRoleServicePort() {
        return new UserRoleUseCase(rolePersistencePort());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), userRoleServicePort(), employeeRestaurantPersistencePort(), authenticatePort());
    }

    @Bean
    public IUserHandler userHandler(){
        return new UserHandlerImpl(userServicePort(), userRequestMapper);
    }

    @Bean
    public IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort() {
        return new EmployeeRestaurantAdapterJPA(employeeRestaurantRepository, employeeRestaurantEntityMapper);
    }
}
