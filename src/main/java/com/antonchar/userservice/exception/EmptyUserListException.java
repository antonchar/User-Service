package com.antonchar.userservice.exception;

public class EmptyUserListException extends RuntimeException {

    public EmptyUserListException(String message) {
        super(message);
    }
}
