package com.pragma.user_service.domain.util.validators;

import com.pragma.user_service.domain.exception.InvalidDataException;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.util.constants.UserValidationConstants;

import java.time.LocalDate;

public class UserValidator {

    private UserValidator() {}

    public static void validateUserData(User user){
        validName(user.getName());
        validLastName(user.getLastName());
        validDni(user.getDni());
        validEmail(user.getEmail());
        validPhoneNumber(user.getPhoneNumber());
        validPassword(user.getPassword());
        validAge(user.getDateOfBirth(), user.getRole().getName());
    }

    private static void validName(String name){
        if(name == null || name.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.NAME_REQUIRED);
        }
    }

    private static void validLastName(String lastName){
        if(lastName == null || lastName.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.LAST_NAME_REQUIRED);
        }
    }

    private static void validDni(String dni){
        if(dni == null || dni.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.DNI_REQUIRED);
        }
        if(!dni.matches(UserValidationConstants.DNI_REGEX)){
            throw new InvalidDataException(UserValidationConstants.DNI_INVALID);
        }
    }

    private static void validEmail(String email){
        if(email == null || email.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.EMAIL_REQUIRED);
        }
        if(!email.matches(UserValidationConstants.EMAIL_REGEX)){
            throw new InvalidDataException(UserValidationConstants.EMAIL_INVALID);
        }
    }

    private static void validPhoneNumber(String phoneNumber){
        if(phoneNumber == null || phoneNumber.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.PHONE_REQUIRED);
        }
        if(!phoneNumber.matches(UserValidationConstants.PHONE_REGEX) ){
            throw new InvalidDataException(UserValidationConstants.PHONE_INVALID);
        }
    }

    private static void validPassword(String password){
        if(password == null || password.isEmpty()){
            throw new InvalidDataException(UserValidationConstants.PASSWORD_REQUIRED);
        }
        if(!password.matches(UserValidationConstants.PASSWORD_REGEX)){
            throw new InvalidDataException(UserValidationConstants.PASSWORD_INVALID);
        }
    }

    private static void validAge(LocalDate dateOfBirth, String role){
        if(role.equals(UserValidationConstants.ROLE_EMPLOYEE) || role.equals(UserValidationConstants.ROLE_CLIENT)){
            return;
        }
        if(dateOfBirth == null){
            throw new InvalidDataException(UserValidationConstants.DATE_OF_BIRTH_REQUIRED);
        }
        LocalDate today = LocalDate.now();
        LocalDate adultDate = today.minusYears(UserValidationConstants.MINIMUM_AGE);
        if (dateOfBirth.isAfter(adultDate)) {
            throw new InvalidDataException(UserValidationConstants.DATE_OF_BIRTH_INVALID);
        }
    }

}
