package com.antonchar.userservice.services;

import org.springframework.stereotype.Service;

import com.antonchar.userservice.services.dto.CurrentUser;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null && hasPermissions(currentUser, userId);
    }

    private boolean hasPermissions(CurrentUser currentUser, Long userId) {
        return currentUser.isAdmin() || currentUser.isSuperAdmin() || isUsersOwnData(currentUser, userId);
    }

    private boolean isUsersOwnData(CurrentUser currentUser, Long userId) {
        return currentUser.isUser() && currentUser.getId().equals(userId);
    }
}
