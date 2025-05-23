package com.pragma.user_service.infrastructure.util.constants.openapi;

public class UserControllerConstantsOpenApi {

    private UserControllerConstantsOpenApi(){}

    public static final String USER_CONTROLLER_TAG = "User Controller";
    public static final String USER_CONTROLLER_SAVE_SUMMARY = "Save User";
    public static final String USER_CONTROLLER_SAVE_DESCRIPTION = "Operation to save a new user";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_201_DESCRIPTION = "User created successfully";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_409_DESCRIPTION = "Conflict, user already exists";
    public static final String USER_CONTROLLER_IS_OWNER_SUMMARY = "Check if user is owner";
    public static final String USER_CONTROLLER_IS_OWNER_DESCRIPTION = "Operation to check if a user is an owner";
    public static final String USER_CONTROLLER_IS_OWNER_RESPONSE_200_DESCRIPTION = "User is an owner";
    public static final String USER_CONTROLLER_LOGIN_SUMMARY = "Login User";
    public static final String USER_CONTROLLER_LOGIN_DESCRIPTION = "Operation to login a user";
    public static final String USER_CONTROLLER_LOGIN_RESPONSE_200_DESCRIPTION = "User logged in successfully";
    public static final String USER_CONTROLLER_LOGIN_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_SAVE_EMPLOYEE_SUMMARY = "Save Employee";
    public static final String USER_CONTROLLER_SAVE_EMPLOYEE_DESCRIPTION = "Operation to save a new employee";
    public static final String USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_201_DESCRIPTION = "Employee created successfully";
    public static final String USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_SAVE_EMPLOYEE_RESPONSE_409_DESCRIPTION = "Conflict, employee already exists";
    public static final String USER_CONTROLLER_SAVE_CLIENT_SUMMARY = "Save Client";
    public static final String USER_CONTROLLER_SAVE_CLIENT_DESCRIPTION = "Operation to save a new client";
    public static final String USER_CONTROLLER_SAVE_CLIENT_RESPONSE_201_DESCRIPTION = "Client created successfully";
    public static final String USER_CONTROLLER_SAVE_CLIENT_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_SAVE_CLIENT_RESPONSE_409_DESCRIPTION = "Conflict, client already exists";
    public static final String USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_SUMMARY = "Get Restaurant ID by Employee ID";
    public static final String USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_DESCRIPTION = "Operation to get restaurant ID by employee ID";
    public static final String USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_RESPONSE_200_DESCRIPTION = "Restaurant ID retrieved successfully";
    public static final String USER_CONTROLLER_GET_ID_RESTAURANT_BY_ID_EMPLOYEE_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_SUMMARY = "Get Phone Number by Client ID";
    public static final String USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_DESCRIPTION = "Operation to get phone number by client ID";
    public static final String USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_RESPONSE_200_DESCRIPTION = "Phone number retrieved successfully";
    public static final String USER_CONTROLLER_GET_PHONE_NUMBER_BY_ID_CLIENT_RESPONSE_404_DESCRIPTION = "Not found, client not found";

}
