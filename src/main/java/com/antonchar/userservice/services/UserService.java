package com.antonchar.userservice.services;

import com.antonchar.userservice.services.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    long getUserNum();

    Page<UserDto> getUsers(Integer pageNumber);

    void deleteUser(Long id);

    UserDto findUser(Long id);

    void saveUser(UserDto user);

    UserDto addUser(UserDto user);

    List<UserDto> findUsers(String name);
}
