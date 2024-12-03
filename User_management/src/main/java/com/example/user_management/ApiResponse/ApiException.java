package com.example.user_management.ApiResponse;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
