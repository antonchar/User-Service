package com.antonchar.userservice.service;

import org.springframework.stereotype.Service;

import com.antonchar.userservice.service.dto.CurrentUser;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean canReadUserDetails(CurrentUser currentUser, Long userId) {
        return currentUser != null && userId != null && hasReadPermission(currentUser, userId);
    }

    @Override
    public boolean canWriteUserDetails(CurrentUser currentUser, Long userId) {
        return currentUser != null && userId != null && hasWritePermission(currentUser, userId);
    }

    private boolean hasReadPermission(CurrentUser currentUser, Long userId) {
        return currentUser.isAdmin() || currentUser.isSuperAdmin() || isUsersOwnData(currentUser, userId);
    }

    private boolean hasWritePermission(CurrentUser currentUser, Long userId) {
        return currentUser.isSuperAdmin() || isUsersOwnData(currentUser, userId);
    }

    private boolean isUsersOwnData(CurrentUser currentUser, Long userId) {
        return currentUser.getId().equals(userId);
    }
}
