package com.antonchar.userservice.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.antonchar.userservice.entities.User;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("h2")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByNameContaining() throws Exception {
        // When
        List<User> users1 = repository.findByNameContaining("Vas");
        List<User> users2 = repository.findByNameContaining("VeryStrangeRequest");

        // Then
        Assert.assertThat(1, is(users1.size()));
        Assert.assertThat(0, is(users2.size()));
    }
}