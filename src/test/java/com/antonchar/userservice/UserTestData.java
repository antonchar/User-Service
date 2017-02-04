package com.antonchar.userservice;

import java.time.LocalDateTime;

import com.antonchar.userservice.entities.User;

public class UserTestData {

    public final static User USER_1, USER_2, USER_3, USER_4;

    static {
        USER_1 = new User(1L, "Vasily Pupkin", 29, false, LocalDateTime.of(2004, 10, 19, 10, 23, 54));
        USER_2 = new User(2L, "Superbratan", 48, true, LocalDateTime.of(2004, 12, 31, 10, 23, 54));
        USER_3 = new User(3L, "John Doe", 34, false, LocalDateTime.of(2007, 10, 19, 10, 23, 54));
        USER_4 = new User(4L, "Avo Pots", 22, true, LocalDateTime.of(2015, 10, 19, 10, 23, 54));
    }
}
