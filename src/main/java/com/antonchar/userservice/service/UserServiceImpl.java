package com.antonchar.userservice.service;

import com.antonchar.userservice.entity.User;
import com.antonchar.userservice.exception.EmptyUserListException;
import com.antonchar.userservice.exception.UserNotFoundException;
import com.antonchar.userservice.repository.UserRepository;
import com.antonchar.userservice.service.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.antonchar.userservice.util.UserUtil.*;
import static org.springframework.util.Assert.isNull;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public long getNum() {
        return userRepository.count();
    }

    @Override
    public Page<UserDto> getPage(Integer pageNumber) {
        notNull(pageNumber, "Page number must not be null");
        if (userRepository.count() == 0L) {
            throw new EmptyUserListException("No users in the database. Please update some entries first.");
        }

        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<User> userPage = userRepository.findAll(request);

        return new PageImpl<>(convert2DtoList(userPage.getContent()), request, userPage.getTotalElements());
    }

    @Override
    public void delete(Long id) {
        notNull(id, "Id must not be null");
        try {
            userRepository.delete(id);
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
    }

    @Override
    public UserDto find(Long id) {
        notNull(id, "Id must not be null");
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return convert2Dto(user);
    }

    @Override
    public UserDto save(UserDto user) {
        notNull(user, "User must not be null");
        isNull(user.getId(), "New user must not have ID");
        User savedUser = userRepository.save(convertFromDto(user));
        return convert2Dto(savedUser);
    }

    @Override
    public UserDto update(UserDto user) {
        notNull(user, "User must not be null");
        User savedUser = userRepository.save(convertFromDto(user));
        return convert2Dto(savedUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        notNull(email, "Email must not be null");
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public List<UserDto> findByName(String name) {
        notNull(name, "Name must not be null");
        return convert2DtoList(userRepository.findByNameContaining(name));
    }
}
