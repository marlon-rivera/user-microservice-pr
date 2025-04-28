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

}
