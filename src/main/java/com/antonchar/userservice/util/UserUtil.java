package com.antonchar.userservice.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.services.dto.UserDto;

public final class UserUtil {

    public static final int PAGE_SIZE = 5;

    private final static ModelMapper MAPPER = new ModelMapper();

    private UserUtil() { }

    public static List<UserDto> convert2DtoList(List<User> users) {
        return users.stream()
            .map(u -> MAPPER.map(u, UserDto.class))
            .collect(Collectors.toList());
    }

    public static UserDto convert2Dto(User user) {
        return MAPPER.map(user, UserDto.class);
    }

    public static User convertFromDto(UserDto user) {
        return MAPPER.map(user, User.class);
    }
}
