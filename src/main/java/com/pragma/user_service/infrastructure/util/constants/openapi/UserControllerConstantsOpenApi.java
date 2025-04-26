package com.pragma.user_service.infrastructure.util.constants.openapi;

public class UserControllerConstantsOpenApi {

    private UserControllerConstantsOpenApi(){}

    public static final String USER_CONTROLLER_TAG = "User Controller";
    public static final String USER_CONTROLLER_DESCRIPTION = "Controller for user operations";
    public static final String USER_CONTROLLER_SAVE_SUMMARY = "Save User";
    public static final String USER_CONTROLLER_SAVE_DESCRIPTION = "Operation to save a new user";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_201_DESCRIPTION = "User created successfully";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_400_DESCRIPTION = "Bad request, invalid input data";
    public static final String USER_CONTROLLER_SAVE_RESPONSE_409_DESCRIPTION = "Conflict, user already exists";


}
