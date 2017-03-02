package com.antonchar.userservice.services;

import com.antonchar.userservice.services.dto.CurrentUser;

public interface PermissionService {

    boolean canReadUserDetails(CurrentUser currentUser, Long userId);

    boolean canWriteUserDetails(CurrentUser currentUser, Long userId);
}
