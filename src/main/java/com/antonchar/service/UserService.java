package com.antonchar.service;

import com.antonchar.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> getUsers(Integer pageNumber);

    void deleteUser(Long id);

    User findUser(Long id);
}
