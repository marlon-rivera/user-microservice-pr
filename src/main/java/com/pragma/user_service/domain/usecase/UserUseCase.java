package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.model.UserRole;
import com.pragma.user_service.domain.spi.IRolePersistencePort;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.domain.util.PasswordEncryptor;
import com.pragma.user_service.domain.util.constants.UserUseCaseConstants;
import com.pragma.user_service.domain.util.constants.UserValidationConstants;
import com.pragma.user_service.domain.util.validators.UserValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    private void saveUser(User user, String roleName) {
        UserValidator.validateUserData(user);
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(UserValidationConstants.EMAIL_ALREADY_EXISTS);
        }
        if (userPersistencePort.existsByDni(user.getDni())) {
            throw new IllegalArgumentException(UserValidationConstants.DNI_ALREADY_EXISTS);
        }
        UserRole userRole = rolePersistencePort.getRoleByName(roleName);
        user.setRole(userRole);
        user.setPassword(PasswordEncryptor.encryptPassword(user.getPassword()));
        userPersistencePort.saveUser(user);
    }

    @Override
    public void saveOwner(User user) {
        saveUser(user, UserUseCaseConstants.ROLE_OWNER);
    }
}
