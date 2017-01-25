package com.antonchar.userservice.exceptions;

public class NoUsersException extends RuntimeException {

    public NoUsersException(String message) {
        super(message);
    }
}
