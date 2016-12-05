package com.antonchar.exceptions;

public class EmptyUsersException extends RuntimeException {

    public EmptyUsersException(String message) {
        super(message);
    }
}
