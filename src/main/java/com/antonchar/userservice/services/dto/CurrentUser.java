package com.antonchar.userservice.services.dto;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    public CurrentUser() {
        super("avo@pots.ee", "$2a$10$vbN0JURxV7hToaWndgHuxOEqvA6x4CjwTNDeLtXABPF3MMLmdW5xS", AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
