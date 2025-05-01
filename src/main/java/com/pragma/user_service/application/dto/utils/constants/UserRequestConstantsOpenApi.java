package com.pragma.user_service.application.dto.utils.constants;

public class UserRequestConstantsOpenApi {

    private UserRequestConstantsOpenApi(){}

    public static final String USER_DESCRIPTION = "User object that needs to be added to the system";
    public static final String USER_NAME_DESCRIPTION = "Name of the user. It should not be empty";
    public static final String USER_LAST_NAME_DESCRIPTION = "Last name of the user. It should not be empty";
    public static final String USER_DNI_DESCRIPTION = "DNI of the user. It should not be empty and must be 10 digits";
    public static final String USER_PHONE_NUMBER_DESCRIPTION = "Phone number of the user. It should not be empty and must be between 10 and 13 digits";
    public static final String USER_DATE_OF_BIRTH_DESCRIPTION = "Date of birth of the user. It should not be empty";
    public static final String USER_EMAIL_DESCRIPTION = "Email of the user. It should not be empty and must be a valid email format";
    public static final String USER_PASSWORD_DESCRIPTION = "Password of the user. It should not be empty";
    public static final String USER_NAME_EXAMPLE = "John";
    public static final String USER_LAST_NAME_EXAMPLE = "Doe";
    public static final String USER_DNI_EXAMPLE = "1092345678";
    public static final String USER_PHONE_NUMBER_EXAMPLE = "35123456789";
    public static final String USER_DATE_OF_BIRTH_EXAMPLE = "1990-01-01";
    public static final String USER_EMAIL_EXAMPLE = "jdoe@gmail.com";
    public static final String USER_PASSWORD_EXAMPLE = "P4ssw0rd";
    public static final String ID_RESTAURANT_DESCRIPTION = "ID of the restaurant. It should not be empty and must be a valid restaurant ID";
    public static final String ID_RESTAURANT_EXAMPLE = "1";

}
