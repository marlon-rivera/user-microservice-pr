package com.pragma.user_service.domain.util.constants;

public class UserUseCaseConstants {

    private UserUseCaseConstants() {}

    public static final String ROLE_OWNER = "OWNER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String INVALID_OWNER_RESTAURANT = "User is not the owner of the restaurant";
    public static final String EMPLOYEE_RESTAURANT_NOT_FOUND = "The employee does not belong to the restaurant";

}
