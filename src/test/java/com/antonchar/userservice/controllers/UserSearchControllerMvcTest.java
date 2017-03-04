package com.antonchar.userservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.config.SecurityConfig;
import com.antonchar.userservice.config.TestConfig;
import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;

import static com.antonchar.userservice.TestDataHelper.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserSearchController.class)
@Import(value = {SecurityConfig.class, TestConfig.class})
@WithUserDetails("admin@example.com")
public class UserSearchControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testEmptySearch() throws Exception {
        mvc.perform(get("/users/search").with(csrf()).accept(MediaType.TEXT_HTML)
            .param("query", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("user_search"));
    }

    @Test
    public void testSearch() throws Exception {
        final List<UserDto> userDtos = convert2DtoList(Arrays.asList(USER_ADM, USER_SADM, USER_USR_BL));
        when(userService.findByName("ABC")).thenReturn(userDtos);

        mvc.perform(get("/users/search").with(csrf()).accept(MediaType.TEXT_HTML)
            .param("query", "ABC"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("query", is("ABC")))
            .andExpect(model().attribute("users", is(userDtos)))
            .andExpect(view().name("user_search"));
    }
}