package com.antonchar.userservice.services;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.repositories.UserRepository;
import com.antonchar.userservice.services.dto.UserDto;
import com.antonchar.userservice.util.exceptions.EmptyUserListException;
import com.antonchar.userservice.util.exceptions.UserNotFoundException;

import static com.antonchar.userservice.UserTestData.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNum() throws Exception {
        when(userRepository.count()).thenReturn(10L);
        assertThat(userService.getNum(), is(10L));
    }

    @Test
    public void testGetPage() throws Exception {
        final List<User> users = Arrays.asList(USER_1, USER_2, USER_3, USER_1, USER_2, USER_3);

        when(userRepository.count()).thenReturn((long) users.size());
        when(userRepository.findAll(any(PageRequest.class)))
            .thenAnswer(invc -> new PageImpl<>(users, invc.getArgumentAt(0, PageRequest.class), users.size()));

        final List<UserDto> expectedDtos = convert2DtoList(users);
        final Page<UserDto> actualPage = userService.getPage(1);

        assertThat(actualPage.getNumber(), is(0));
        assertThat(actualPage.getTotalPages(), is(2));
        assertThat(actualPage.getTotalElements(), is(6L));
        assertThat(actualPage.getContent(), is(expectedDtos));
    }

    @Test(expected = EmptyUserListException.class)
    public void testGetPageEmptyDB() throws Exception {
        when(userRepository.count()).thenReturn(0L);
        userService.getPage(1);
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(userRepository).delete(1L);
        userService.delete(1L);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteNotExisting() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(userRepository).delete(1L);
        userService.delete(1L);
    }

//    @Test
//    public void find() throws Exception {
//
//    }
//
//    @Test
//    public void save() throws Exception {
//
//    }
//
//    @Test
//    public void update() throws Exception {
//
//    }
//
//    @Test
//    public void findByName() throws Exception {
//
//    }
}