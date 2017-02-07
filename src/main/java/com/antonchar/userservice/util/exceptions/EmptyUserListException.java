package com.antonchar.userservice.util.exceptions;

public class EmptyUserListException extends RuntimeException {

    public EmptyUserListException(String message) {
        super(message);
    }
}
