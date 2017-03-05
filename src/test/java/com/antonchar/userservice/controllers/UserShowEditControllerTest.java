package com.antonchar.userservice.controllers;

import com.antonchar.userservice.config.SecurityConfig;
import com.antonchar.userservice.config.TestConfig;
import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;
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

import static com.antonchar.userservice.TestDataHelper.USER_ADM;
import static com.antonchar.userservice.TestDataHelper.getNewUserAdm;
import static com.antonchar.userservice.util.UserUtil.convert2Dto;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserShowEditController.class)
@Import(value = {SecurityConfig.class, TestConfig.class})
@WithUserDetails("admin@example.com")
public class UserShowEditControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testShowUser() throws Exception {
        when(userService.find(1L)).thenReturn(convert2Dto(USER_ADM));

        mvc.perform(get("/user/1").with(csrf()).accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attribute("existingUser", is(convert2Dto(USER_ADM))))
            .andExpect(model().attribute("saved", is(false)))
            .andExpect(view().name("user"));
    }

    @Test
    public void testEditUserInitState() throws Exception {
        UserDto sessionUser = convert2Dto(getNewUserAdm(1L));

        mvc.perform(post("/user/edit").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("existingUser", sessionUser)
            .param("state", "init"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("existingUser", is(sessionUser)))
            .andExpect(view().name("user_edit"));
    }

    @Test
    public void testEditUserValidDetails() throws Exception {
        when(userService.update(any(UserDto.class)))
            .thenAnswer(invc -> invc.getArgumentAt(0, UserDto.class));

        mvc.perform(post("/user/edit").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("existingUser", convert2Dto(getNewUserAdm(1L)))
            .param("name", "DummyName")
            .param("surname", "DummySurname")
            .param("age", "23"))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attributeDoesNotExist("existingUser"))
            .andExpect(redirectedUrl("/user/1?saved=true"));
    }

    @Test
    public void testEditUserInvalidDetails() throws Exception {
        mvc.perform(post("/user/edit").with(csrf()).accept(MediaType.TEXT_HTML)
            .sessionAttr("existingUser", convert2Dto(getNewUserAdm(1L)))
            .param("age", "236"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("existingUser", is(convert2Dto(getNewUserAdm(1L).setAge(236)))))
            .andExpect(view().name("user_edit"));
    }

}