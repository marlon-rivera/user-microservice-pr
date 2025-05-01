package com.pragma.user_service.domain.usecase;

import com.pragma.user_service.domain.api.IUserRoleServicePort;
import com.pragma.user_service.domain.api.IUserServicePort;
import com.pragma.user_service.domain.exception.ResourceConflictException;
import com.pragma.user_service.domain.exception.InvalidDataException;
import com.pragma.user_service.domain.model.*;
import com.pragma.user_service.domain.spi.IEmployeeRestaurantPersistencePort;
import com.pragma.user_service.domain.spi.IUserPersistencePort;
import com.pragma.user_service.domain.util.PasswordEncryptor;
import com.pragma.user_service.domain.util.constants.UserUseCaseConstants;
import com.pragma.user_service.domain.util.constants.UserValidationConstants;
import com.pragma.user_service.domain.util.validators.UserValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserRoleServicePort userRoleServicePort;
    private final IEmployeeRestaurantPersistencePort employeeRestaurantPersistencePort;


    private User saveUser(User user, String roleName) {
        UserRole userRole = userRoleServicePort.getRoleByName(roleName);
        user.setRole(userRole);
        UserValidator.validateUserData(user);
        if (userPersistencePort.existsByEmail(user.getEmail())) {
            throw new ResourceConflictException(UserValidationConstants.EMAIL_ALREADY_EXISTS);
        }
        if (userPersistencePort.existsByDni(user.getDni())) {
            throw new ResourceConflictException(UserValidationConstants.DNI_ALREADY_EXISTS);
        }
        user.setPassword(PasswordEncryptor.encryptPassword(user.getPassword()));
        return userPersistencePort.saveUser(user);
    }

    @Override
    public void saveOwner(User user) {
        saveUser(user, UserUseCaseConstants.ROLE_OWNER);
    }

    @Override
    public boolean isOwner(Long userId) {
        return userPersistencePort.findById(userId)
                .map(user -> user.getRole().getName().equals(UserUseCaseConstants.ROLE_OWNER))
                .orElse(false);
    }
  
    @Override
    public Auth login(String email, String password) {
        Auth auth = userPersistencePort.authenticateUser(email, password);
        if (auth == null) {
            throw new InvalidDataException(UserValidationConstants.INVALID_CREDENTIALS);
        }
        return auth;
    }

    @Override
    public void saveEmployee(User user, Long restaurantId) {
        boolean validOwner = userPersistencePort.validateOwnerRestaurant(restaurantId);
        if (!validOwner) {
            throw new InvalidDataException(UserUseCaseConstants.INVALID_OWNER_RESTAURANT);
        }
        User userSaved = saveUser(user, UserUseCaseConstants.ROLE_EMPLOYEE);
        saveEmployeeRestaurant(userSaved.getId(), restaurantId);
    }

    private void saveEmployeeRestaurant(Long idUser, Long restaurantId) {
        EmployeeRestaurant employeeRestaurant = new EmployeeRestaurant(new EmployeeRestaurantId(idUser, restaurantId));
        employeeRestaurantPersistencePort.saveEmployeeRestaurant(employeeRestaurant);
    }
}
