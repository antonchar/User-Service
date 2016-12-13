package com.antonchar.exceptions;

public class NoUsersException extends RuntimeException {

    public NoUsersException(String message) {
        super(message);
    }
}
