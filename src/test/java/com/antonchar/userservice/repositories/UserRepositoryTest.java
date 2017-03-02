package com.antonchar.userservice.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.antonchar.userservice.entities.User;

import static com.antonchar.userservice.TestDataHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testCount() throws Exception {
        assertThat(repository.count(), is(4L));
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> allUsers = repository.findAll();
        assertThat(allUsers, containsInAnyOrder(USER_ADM, USER_SADM, USER_USR_BL, USER_USR));
    }

    @Test
    public void testFindFirstPage() throws Exception {
        final int pageNumber = 0;
        PageRequest request = new PageRequest(pageNumber, 3, Sort.Direction.ASC, "id");
        Page<User> userPage = repository.findAll(request);

        assertThat(userPage.getNumber(), is(pageNumber));
        assertThat(userPage.getTotalPages(), is(2));
        assertThat(userPage.getContent(), contains(USER_ADM, USER_SADM, USER_USR_BL));
    }

    @Test
    public void testFindLastPage() throws Exception {
        final int pageNumber = 1;
        PageRequest request = new PageRequest(pageNumber, 3, Sort.Direction.ASC, "id");
        Page<User> userPage = repository.findAll(request);

        assertThat(userPage.getNumber(), is(pageNumber));
        assertThat(userPage.getContent(), contains(USER_USR));
    }

    @Test
    public void testFindTooBigPage() throws Exception {
        final int pageNumber = 100;
        PageRequest request = new PageRequest(pageNumber, 3, Sort.Direction.ASC, "id");
        Page<User> userPage = repository.findAll(request);

        assertThat(userPage.getNumber(), is(pageNumber));
        assertThat(userPage.getContent(), empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindNegativePage() throws Exception {
        PageRequest request = new PageRequest(-1, 3, Sort.Direction.ASC, "id");
        repository.findAll(request);
    }

    @Test
    public void testDeleteExisting() throws Exception {
        repository.delete(1L);
        assertThat(repository.count(), is(3L));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteMissing() throws Exception {
        repository.delete(1000L);
    }

    @Test
    public void testFindExisting() throws Exception {
        User user = repository.findOne(1L);
        assertThat(user, is(USER_ADM));
    }

    @Test
    public void testFindMissing() throws Exception {
        User user = repository.findOne(100L);
        assertThat(user, nullValue());
    }

    @Test
    public void testSave() throws Exception {
        User user = repository.save(getNewUserUsr(null));
        //TODO: check all properties
        assertThat(user.getId(), greaterThan(4L));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = USER_ADM.setAge(68);
        User savedUser = repository.save(user);
        assertThat(savedUser, is(user));
    }

    @Test
    public void testFindByNameExisting() throws Exception {
        List<User> users = repository.findByNameContaining("o");
        assertThat(users, containsInAnyOrder(USER_USR_BL, USER_USR));
    }

    @Test
    public void testFindByNameMissing() throws Exception {
        List<User> users = repository.findByNameContaining("No way");
        assertThat(users, empty());
    }
}