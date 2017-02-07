package com.antonchar.userservice.util.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
