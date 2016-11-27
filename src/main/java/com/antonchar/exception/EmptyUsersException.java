package com.antonchar.exception;

public class EmptyUsersException extends RuntimeException {

    public EmptyUsersException(String message) {
        super(message);
    }
}
