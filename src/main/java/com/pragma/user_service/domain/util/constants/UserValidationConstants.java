package com.pragma.user_service.domain.util.constants;

public class UserValidationConstants {

    private UserValidationConstants(){}

    public static final String NAME_REQUIRED = "The name is required";
    public static final String LAST_NAME_REQUIRED = "The last name is required";
    public static final String EMAIL_REQUIRED = "The email is required";
    public static final String EMAIL_INVALID = "The email is invalid";
    public static final String EMAIL_ALREADY_EXISTS = "The email already exists";
    public static final String PHONE_REQUIRED = "The phone is required";
    public static final String PHONE_INVALID = "The phone is invalid";
    public static final String PASSWORD_REQUIRED = "The password is required";
    public static final String PASSWORD_INVALID = "The password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character";
    public static final String ROLE_REQUIRED = "The role is required";
    public static final String ROLE_INVALID = "The role is invalid";
    public static final String DATE_OF_BIRTH_REQUIRED = "The date of birth is required";
    public static final String DATE_OF_BIRTH_INVALID = "The date of birth is invalid";
    public static final String DNI_REQUIRED = "The dni is required";
    public static final String DNI_INVALID = "The dni is invalid";
    public static final String DNI_ALREADY_EXISTS = "The dni already exists";
    public static final String PHONE_LENGTH = "The phone number must be between 10 and 13 digits";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PHONE_REGEX = "^(\\+\\d{12}|57\\d{10}|\\d{10})$";
    public static final String DNI_REGEX = "^[0-9]{10}$";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final int MINIMUM_AGE = 18;
    public static final String INVALID_CREDENTIALS = "Invalid credentials";

}
