package com.pragma.user_service.domain.util.validators;

import com.pragma.user_service.domain.exception.InvalidDataException;
import com.pragma.user_service.domain.model.User;
import com.pragma.user_service.domain.util.constants.UserValidationConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void validateUserData_WithValidUser_ShouldNotThrowException() {
        User user = User.builder()
                .name("Juan")
                .lastName("Perez")
                .dni("1234567890")
                .email("juan@example.com")
                .phoneNumber("+573001234567")
                .password("Password123*")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .build();

        assertDoesNotThrow(() -> UserValidator.validateUserData(user));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validName_WithNullOrEmptyName_ShouldThrowException(String name) {
        User user = createValidUser();
        user.setName(name);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.NAME_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validLastName_WithNullOrEmptyLastName_ShouldThrowException(String lastName) {
        User user = createValidUser();
        user.setLastName(lastName);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.LAST_NAME_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validDni_WithNullOrEmptyDni_ShouldThrowException(String dni) {
        User user = createValidUser();
        user.setDni(dni);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.DNI_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC123", "12345", "ABC12345*"})
    void validDni_WithInvalidDni_ShouldThrowException(String dni) {
        User user = createValidUser();
        user.setDni(dni);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.DNI_INVALID, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validEmail_WithNullOrEmptyEmail_ShouldThrowException(String email) {
        User user = createValidUser();
        user.setEmail(email);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.EMAIL_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "user@", "user@domain", "@domain.com"})
    void validEmail_WithInvalidEmail_ShouldThrowException(String email) {
        User user = createValidUser();
        user.setEmail(email);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.EMAIL_INVALID, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validPhoneNumber_WithNullOrEmptyPhoneNumber_ShouldThrowException(String phoneNumber) {
        User user = createValidUser();
        user.setPhoneNumber(phoneNumber);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.PHONE_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "ABC123456789", "+57123", "+5712345678901234"})
    void validPhoneNumber_WithInvalidPhoneNumber_ShouldThrowException(String phoneNumber) {
        User user = createValidUser();
        user.setPhoneNumber(phoneNumber);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.PHONE_INVALID, exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void validPassword_WithNullOrEmptyPassword_ShouldThrowException(String password) {
        User user = createValidUser();
        user.setPassword(password);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.PASSWORD_REQUIRED, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"password", "PASSWORD", "Password", "Password1", "Passw*", "12345*"})
    void validPassword_WithInvalidPassword_ShouldThrowException(String password) {
        User user = createValidUser();
        user.setPassword(password);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.PASSWORD_INVALID, exception.getMessage());
    }

    @Test
    void validAge_WithNullDateOfBirth_ShouldThrowException() {
        User user = createValidUser();
        user.setDateOfBirth(null);

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.DATE_OF_BIRTH_REQUIRED, exception.getMessage());
    }

    @Test
    void validAge_WithDateOfBirthTooRecent_ShouldThrowException() {
        User user = createValidUser();
        user.setDateOfBirth(LocalDate.now().minusYears(UserValidationConstants.MINIMUM_AGE - 1));

        InvalidDataException exception = assertThrows(InvalidDataException.class,
                () -> UserValidator.validateUserData(user));

        assertEquals(UserValidationConstants.DATE_OF_BIRTH_INVALID, exception.getMessage());
    }

    private User createValidUser() {
        return User.builder()
                .name("Juan")
                .lastName("Perez")
                .dni("1234567890")
                .email("juan@example.com")
                .phoneNumber("+573001234567")
                .password("Password123*")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .build();
    }
}