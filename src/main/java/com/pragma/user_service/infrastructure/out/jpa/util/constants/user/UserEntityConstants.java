package com.pragma.user_service.infrastructure.out.jpa.util.constants.user;

public class UserEntityConstants {

    private UserEntityConstants() {}

    public static final String TABLE_NAME = "user";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final int NAME_LENGTH = 100;
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final int LAST_NAME_LENGTH = 100;
    public static final String DNI_COLUMN = "dni";
    public static final int DNI_LENGTH = 10;
    public static final String PHONE_NUMBER_COLUMN = "phone_number";
    public static final int PHONE_NUMBER_LENGTH = 13;
    public static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
    public static final String EMAIL_COLUMN = "email";
    public static final int EMAIL_LENGTH = 100;
    public static final String PASSWORD_COLUMN = "password";
    public static final String USER_ROLE_ID_COLUMN = "user_role_id";

}
