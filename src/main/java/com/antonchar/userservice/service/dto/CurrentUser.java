package com.antonchar.userservice.service.dto;

import lombok.Getter;

import org.springframework.security.core.authority.AuthorityUtils;

import com.antonchar.userservice.entity.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final Long id;
    @Getter
    private final String fullName;
    private final User.Role role;

    public CurrentUser(User user) {
        super(
            user.getEmail(),
            user.getPwdHash(),
            true,
            true,
            true,
            !user.getBlocked(),
            AuthorityUtils.createAuthorityList(user.getRole().name())
        );
        this.id = user.getId();
        this.role = user.getRole();
        this.fullName = user.getName() + " " + user.getSurname();
    }

    public boolean isUser() {
        return this.role == User.Role.USER;
    }

    public boolean isAdmin() {
        return this.role == User.Role.ADMIN;
    }

    public boolean isSuperAdmin() {
        return this.role == User.Role.SUPERADMIN;
    }
}
