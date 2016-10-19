package com.antonchar.service;

import com.antonchar.entity.User;
import com.antonchar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 3;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsers(Integer pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return userRepository.findAll(request);
    }
}
