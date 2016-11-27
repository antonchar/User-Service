package com.antonchar.service;

import com.antonchar.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    long getUserNum();

    Page<User> getUsers(Integer pageNumber);

    void deleteUser(Long id);

    User findUser(Long id);

    void saveUser(User user);

    User addUser(User user);

    List<User> findUsers(String name);
}
