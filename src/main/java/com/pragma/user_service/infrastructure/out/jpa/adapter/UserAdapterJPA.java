package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.Auth;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.spi.IAuthenticatePort;
import com.pragma.user_service.domain.spi.IJWTPort;
import com.pragma.user_service.domain.spi.IRestaurantPersistencePort;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapterJPA implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IJWTPort jwtPort;
    private final IAuthenticatePort authenticatePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        UserEntity saved = userRepository.save(userEntity);
        return userEntityMapper.toUser(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDni(String dni) {
        return userRepository.existsByDni(dni);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityMapper.toOptionalUser(userRepository.findById(id));
    }

    @Override
    public Auth authenticateUser(String email, String password) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        if (userEntityOptional.isEmpty()) {
            return null;
        }
        UserEntity userEntity = userEntityOptional.get();
        authenticatePort.authenticateUser(userEntity.getId().toString(), password);
        String token = jwtPort.getToken(userEntity.getId().toString(), userEntity.getRole().getName());
        return new Auth(token);
    }

    @Override
    public boolean validateOwnerRestaurant(Long restaurantId) {
        return restaurantPersistencePort.validateOwnerRestaurant(restaurantId);
    }
}
