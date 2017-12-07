package com.antonchar.userservice.controller;

import com.antonchar.userservice.config.SecurityConfig;
import com.antonchar.userservice.IntegrationTestConfig;
import com.antonchar.userservice.service.UserService;
import com.antonchar.userservice.service.dto.UserDto;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.antonchar.userservice.TestDataHelper.USER_ADM;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserCreateDeleteController.class)
@Import(value = {SecurityConfig.class, IntegrationTestConfig.class})
@WithUserDetails("superadmin@example.com")
@Ignore
public class UserCreateDeleteControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean(name = "userValidator")
    private Validator userValidator;

    @MockBean
    private PasswordEncoder dummyEncoder;

    @Test
    public void testAddUserGet() throws Exception {
        mvc.perform(get("/user/add").with(csrf()).accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attribute("newUser", is(new UserDto())))
            .andExpect(view().name("user_add_starter"));
    }

    @Test
    public void testAddUserPost() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userService.save(any(UserDto.class)))
            .thenAnswer(invc -> invc.getArgumentAt(0, UserDto.class).setId(1L));

        when(dummyEncoder.encode(anyString())).thenReturn("dummyHash");

        mvc.perform(post("/user/add").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("newUser", new UserDto())
            .param("email", "email@example.com")
            .param("name", "DummyName")
            .param("surname", "DummySurname")
            .param("age", "23"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user/1?saved=true"));
    }

    @Test
    @WithUserDetails("superadmin@example.com")
    public void testAddInvalidHibernateUserPost() throws Exception {
        mvc.perform(post("/user/add").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("newUser", new UserDto())
            .param("age", "23"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("newUser", is(new UserDto().setAge(23))))
            .andExpect(view().name("user_add_validated"));
    }

    @Test
    @WithUserDetails("superadmin@example.com")
    public void testAddTakenEmailUserPost() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.of(USER_ADM));

        mvc.perform(post("/user/add").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("newUser", new UserDto())
            .param("email", USER_ADM.getEmail())
            .param("name", "DummyName")
            .param("surname", "DummySurname")
            .param("age", "23"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("newUser", is(
                // TODO: REFACTOR
                new UserDto()
                    .setEmail(USER_ADM.getEmail())
                    .setName("DummyName")
                    .setSurname("DummySurname")
                    .setAge(23)
                )
            ))
            .andExpect(view().name("user_add_validated"));
    }

    @Test
    public void testDeleteFromList() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        mvc.perform(post("/user/delete").with(csrf()).accept(MediaType.TEXT_HTML)
            .param("id", "12")
            .param("page", "10"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users/pages/10"));

        verify(userService, only()).delete(any(Long.class));
    }

    @Test
    public void testDeleteFromSearch() throws Exception {
        doNothing().when(userService).delete(any(Long.class));

        mvc.perform(post("/user/delete").with(csrf()).accept(MediaType.TEXT_HTML)
            .param("id", "12")
            .param("query", "ABC"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/users/search?query=ABC"));

        verify(userService, only()).delete(any(Long.class));
    }
}