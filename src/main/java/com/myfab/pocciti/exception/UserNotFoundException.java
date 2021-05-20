package com.myfab.pocciti.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        this("User does not exists");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
