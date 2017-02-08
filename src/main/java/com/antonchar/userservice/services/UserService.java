package com.antonchar.userservice.services;

import com.antonchar.userservice.services.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    long getNum();

    Page<UserDto> getPage(Integer pageNumber);

    void delete(Long id);

    UserDto find(Long id);

    UserDto save(UserDto user);

    UserDto update(UserDto user);

    List<UserDto> findByName(String name);
}
