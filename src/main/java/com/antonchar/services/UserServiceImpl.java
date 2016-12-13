package com.antonchar.services;

import com.antonchar.entities.User;
import com.antonchar.exceptions.NoUsersException;
import com.antonchar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 5;

    @Autowired
    private UserRepository userRepository;

    @Override
    public long getUserNum() {
        return userRepository.count();
    }

    @Override
    public Page<User> getUsers(Integer pageNumber) {
        if (userRepository.count() == 0L) {
            throw new NoUsersException("No users in the database. Please add some entries first.");
        }

        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return userRepository.findAll(request);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsers(String name) {
        return userRepository.findByNameContaining(name);
    }
}
