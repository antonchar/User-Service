package com.antonchar.userservice.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import static com.antonchar.userservice.TestDataHelper.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static com.antonchar.userservice.util.UserUtil.convert2Dto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
        final List<User> users = Arrays.asList(USER_ADM, USER_SADM, USER_USR_BL, USER_ADM, USER_SADM, USER_USR_BL);

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
        verify(userRepository, only()).delete(1L);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteNotExisting() throws Exception {
        doThrow(EmptyResultDataAccessException.class).when(userRepository).delete(1L);
        userService.delete(1L);
    }

    @Test
    public void testFindExisting() throws Exception {
        when(userRepository.findOne(1L)).thenReturn(USER_ADM);
        assertThat(userService.find(1L), is(convert2Dto(USER_ADM)));
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindMissing() throws Exception {
        when(userRepository.findOne(1L)).thenReturn(null);
        userService.find(1L);
    }

    @Test
    public void testSave() throws Exception {
        final User user = getNewUserUsr(null);
        final User savedUser = getNewUserUsr(1L);

        when(userRepository.save(user)).thenReturn(savedUser);
        assertThat(userService.save(convert2Dto(user)), is(convert2Dto(savedUser)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveWithId() throws Exception {
      userService.save(convert2Dto(USER_ADM));
    }

    @Test
    public void testUpdate() throws Exception {
        when(userRepository.save(USER_ADM)).thenReturn(USER_ADM);
        final UserDto updatedUser = userService.update(convert2Dto(USER_ADM));
        assertThat(updatedUser, is(convert2Dto(USER_ADM)));
    }

    @Test
    public void testFindByEmail() throws Exception {
        when(userRepository.findByEmail(anyString())).thenReturn(USER_ADM);
        assertThat(userService.findByEmail("dummy@email"), is(Optional.of(USER_ADM)));
    }

    @Test
    public void testFindByName() throws Exception {
        when(userRepository.findByNameContaining("ABC")).thenReturn(Collections.singletonList(USER_ADM));
        assertThat(userService.findByName("ABC"), is(Collections.singletonList(convert2Dto(USER_ADM))));
    }
}