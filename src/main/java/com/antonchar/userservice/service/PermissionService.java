package com.antonchar.userservice.service;

import com.antonchar.userservice.service.dto.CurrentUser;

public interface PermissionService {

    boolean canReadUserDetails(CurrentUser currentUser, Long userId);

    boolean canWriteUserDetails(CurrentUser currentUser, Long userId);
}
