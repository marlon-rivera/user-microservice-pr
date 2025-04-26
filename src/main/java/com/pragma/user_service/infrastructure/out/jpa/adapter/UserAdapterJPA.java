package com.pragma.user_service.infrastructure.out.jpa.adapter;

import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user_service.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.user_service.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapterJPA implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toUserEntity(user);
        System.out.println("UserEntity.name: " + userEntity.getName());
        System.out.println("UserEntity.email: " + userEntity.getEmail());
        System.out.println("UserEntity.dni: " + userEntity.getDni());
        System.out.println("UserEntity.password: " + userEntity.getPassword());
        System.out.println("UserEntity.role: " + userEntity.getRole());
        System.out.println("UserEntity.phone: " + userEntity.getPhoneNumber());
        System.out.println("UserEntity.dateOfBirth: " + userEntity.getDateOfBirth());

        userRepository.save(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDni(String dni) {
        return userRepository.existsByDni(dni);
    }
}
