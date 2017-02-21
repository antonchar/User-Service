package com.antonchar.userservice.services.dto;

import lombok.Getter;

import org.springframework.security.core.authority.AuthorityUtils;

import com.antonchar.userservice.entities.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final Long id;
    private final User.Role role;

    public CurrentUser(User user) {
        super(
            user.getEmail(),
            user.getPwdHash(),
            true,
            true,
            true,
            user.getIsBlocked(),
            AuthorityUtils.createAuthorityList(user.getRole().name())
        );
        this.id = user.getId();
        this.role = user.getRole();
    }

    public boolean isUser() {
        return this.role == User.Role.USER;
    }
}
