package com.antonchar.userservice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
