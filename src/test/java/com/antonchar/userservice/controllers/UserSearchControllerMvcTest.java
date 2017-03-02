package com.antonchar.userservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;

import static com.antonchar.userservice.TestDataHelper.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserSearchController.class)
@ActiveProfiles("disabled-security")
public class UserSearchControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testEmptySearch() throws Exception {
        mvc.perform(get("/users/search").accept(MediaType.TEXT_HTML)
            .param("query", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("user_search"));
    }

    @Test
    public void testSearch() throws Exception {
        final List<UserDto> userDtos = convert2DtoList(Arrays.asList(USER_ADM, USER_SADM, USER_USR_BL));
        when(userService.findByName("ABC")).thenReturn(userDtos);

        mvc.perform(get("/users/search").accept(MediaType.TEXT_HTML)
            .param("query", "ABC"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("query", is("ABC")))
            .andExpect(model().attribute("users", is(userDtos)))
            .andExpect(view().name("user_search"));
    }
}