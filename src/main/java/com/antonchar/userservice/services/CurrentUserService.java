package com.antonchar.userservice.services;

import com.antonchar.userservice.services.dto.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
