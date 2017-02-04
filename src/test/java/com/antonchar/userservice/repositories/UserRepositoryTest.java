package com.antonchar.userservice.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.antonchar.userservice.entities.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    //private final static User USER_1 = new

    @Test
    public void testFindAll() throws Exception {
        List<User> allUsers = repository.findByNameContaining("Vas");
        assertThat(allUsers, hasSize(12));
    }

    @Test
    public void testFindPage() throws Exception {
        PageRequest request = new PageRequest(0, 3, Sort.Direction.ASC, "id");
        Page<User> userPage = repository.findAll(request);
    }
}