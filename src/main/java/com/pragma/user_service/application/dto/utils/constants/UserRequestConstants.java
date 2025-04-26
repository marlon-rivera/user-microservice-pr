package com.pragma.user_service.application.dto.utils.constants;

public class UserRequestConstants {

    private UserRequestConstants() {}

    public static final String NAME_MUST_MANDATORY = "The name must be mandatory";
    public static final String LAST_NAME_MUST_MANDATORY = "The last name must be mandatory";
    public static final String DNI_MUST_MANDATORY = "The dni must be mandatory";
    public static final String PHONE_NUMBER_MUST_MANDATORY = "The phone number must be mandatory";
    public static final String DATE_OF_BIRTH_MUST_MANDATORY = "The date of birth must be mandatory";
    public static final String EMAIL_MUST_MANDATORY = "The email must be mandatory";
    public static final String PASSWORD_MUST_MANDATORY = "The password must be mandatory";
    public static final String DNI_MUST_BE_VALID = "The dni must have 10 digits";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String REGEX_PHONE_NUMBER = "^(\\+\\d{12}|57\\d{10}|\\d{10})$";
    public static final String REGEX_DNI = "^[0-9]{10}$";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
}
