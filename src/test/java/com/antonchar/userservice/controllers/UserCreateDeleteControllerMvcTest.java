package com.antonchar.userservice.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserCreateDeleteController.class)
public class UserCreateDeleteControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Test
    public void testAddUserGet() throws Exception {
        mvc.perform(get("/user/add").accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attribute("newUser", is(new UserDto())))
            .andExpect(view().name("user_add_form"));
    }

    @Test
    public void testAddUserPost() throws Exception {
        when(userService.save(any(UserDto.class)))
            .thenAnswer(invc -> invc.getArgumentAt(0, UserDto.class).setId(1L));

        mvc.perform(post("/user/add").accept(MediaType.TEXT_HTML)
            .sessionAttr("newUser", new UserDto())
            .param("name", "DummyName")
            .param("age", "23")
            .param("admin", "true"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/1?saved=true"));
    }

    @Test
    public void testAddInvalidUserPost() throws Exception {
        mvc.perform(post("/user/add").accept(MediaType.TEXT_HTML)
            .sessionAttr("newUser", new UserDto())
            .param("age", "23"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("newUser", is(new UserDto().setAge(23))))
            .andExpect(view().name("user_add"));
    }

    @Test
    public void testDeleteFromList() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        mvc.perform(post("/user/delete").accept(MediaType.TEXT_HTML)
            .param("id", "12")
            .param("page", "10"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users/pages/10"));

        verify(userService, only()).delete(any(Long.class));
    }

    @Test
    public void testDeleteFromSearch() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        mvc.perform(post("/user/delete").accept(MediaType.TEXT_HTML)
            .param("id", "12")
            .param("query", "ABC"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users/search?query=ABC"));

        verify(userService, only()).delete(any(Long.class));
    }
}