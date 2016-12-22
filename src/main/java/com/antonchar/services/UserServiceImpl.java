package com.antonchar.services;

import com.antonchar.entities.User;
import com.antonchar.exceptions.NoUsersException;
import com.antonchar.repositories.UserRepository;
import com.antonchar.services.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public long getUserNum() {
        return userRepository.count();
    }

    @Override
    public Page<UserDto> getUsers(Integer pageNumber) {
        if (userRepository.count() == 0L) {
            throw new NoUsersException("No users in the database. Please add some entries first.");
        }

        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<User> userPage = userRepository.findAll(request);

        return new PageImpl<>(convertToDtoList(userPage.getContent()), request, userPage.getTotalElements());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserDto findUser(Long id) {
        User user = userRepository.findOne(id);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void saveUser(UserDto user) {
        User userEntity = mapper.map(user, User.class);
        userRepository.save(userEntity);
    }

    @Override
    public UserDto addUser(UserDto user) {
        User userEntity = mapper.map(user, User.class);
        return mapper.map(userRepository.save(userEntity) , UserDto.class);
    }

    @Override
    public List<UserDto> findUsers(String name) {
        return convertToDtoList(userRepository.findByNameContaining(name));
    }

    private List<UserDto> convertToDtoList(List<User> users) {
        return users.stream()
                .map(e -> mapper.map(e, UserDto.class))
                .collect(Collectors.toList());
    }
}
