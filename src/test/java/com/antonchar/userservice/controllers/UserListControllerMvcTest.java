package com.antonchar.userservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;
import com.antonchar.userservice.util.exceptions.EmptyUserListException;

import static com.antonchar.userservice.UserTestData.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserListController.class)
public class UserListControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUserList() throws Exception {
        final PageRequest request = new PageRequest(0, 2, Sort.Direction.ASC, "id");
        final List<UserDto> userDtos = convert2DtoList(Arrays.asList(USER_1, USER_2, USER_3));
        final Page<UserDto> userPages = new PageImpl<>(userDtos, request, userDtos.size());

        when(userService.getNum()).thenReturn(3L);
        when(userService.getPage(1)).thenReturn(userPages);

        mvc.perform(get("/users/pages/1").accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attribute("userNum", is(3L)))
            .andExpect(model().attribute("userPages", is(userPages)))
            .andExpect(model().attribute("beginIndex", is(1)))
            .andExpect(model().attribute("currentIndex", is(1)))
            .andExpect(model().attribute("endIndex", is(2)))
            .andExpect(view().name("users"));
    }

    @Test
    public void testNoUsers() throws Exception {
        when(userService.getNum()).thenReturn(0L);
        when(userService.getPage(1)).thenThrow(new EmptyUserListException("Dummy"));

        mvc.perform(get("/users/pages/1").accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attributeDoesNotExist("userPages"))
            .andExpect(model().attributeExists("emptyDB"))
            .andExpect(view().name("error"));
    }
}
