package com.antonchar.userservice;

import java.time.LocalDateTime;

import com.antonchar.userservice.entities.User;

public class UserTestData {

    public final static User USER_1, USER_2, USER_3, USER_4;

    static {
        USER_1 = new User(
            1L, "vasilypup@example.com", "$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq",
            "Vasily", "Pupkin", 29, User.Role.ADMIN, false,
            LocalDateTime.of(2004, 10, 19, 10, 23, 54));

        USER_2 = new User(
            2L, "superbratan@example.com", "$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq",
            "Super", "Bratan", 48, User.Role.SUPERADMIN, false,
            LocalDateTime.of(2005, 12, 31, 10, 23, 54));

        USER_3 = new User(
            3L, "johnnny@example.com", "$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq",
            "John", "Doe", 33, User.Role.USER, true,
            LocalDateTime.of(2006, 10, 19, 10, 23, 54));

        USER_4 = new User(
            4L, "avo.pots@example.com", "$2a$04$jzeBznajaIeT5SOVYsx2XOy0h7ryBePQ/LZ4xqWlT4d457BCQjTZq",
            "Avo", "Pots", 22, User.Role.USER, false,
            LocalDateTime.of(2007, 12, 31, 10, 23, 54));
    }

    public static User getNewUser(Long id) {
        return new User(
            id, "new@example.com", "dummyHash", "new", "user",
            33, User.Role.USER, false, LocalDateTime.of(2000, 10, 19, 10, 23, 54)
        );
    }
}
