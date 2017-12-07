package com.antonchar.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.antonchar.userservice.entity.User;
import com.antonchar.userservice.service.dto.UserDto;

public interface UserService {

    long getNum();

    Page<UserDto> getPage(Integer pageNumber);

    void delete(Long id);

    UserDto find(Long id);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    Optional<User> findByEmail(String email);

    List<UserDto> findByName(String name);
}
