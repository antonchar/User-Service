package com.antonchar.userservice.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.config.SecurityConfig;
import com.antonchar.userservice.IntegrationTestConfig;
import com.antonchar.userservice.service.UserService;
import com.antonchar.userservice.service.dto.UserDto;
import com.antonchar.userservice.exception.EmptyUserListException;

import static com.antonchar.userservice.TestDataHelper.*;
import static com.antonchar.userservice.util.UserUtil.convert2DtoList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserListController.class)
@Import(value = {SecurityConfig.class, IntegrationTestConfig.class})
@WithUserDetails("admin@example.com")
@Ignore
public class UserListControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testUserList() throws Exception {
        final PageRequest request = new PageRequest(0, 2, Sort.Direction.ASC, "id");
        final List<UserDto> userDtos = convert2DtoList(Arrays.asList(USER_ADM, USER_SADM, USER_USR_BL));
        final Page<UserDto> userPages = new PageImpl<>(userDtos, request, userDtos.size());

        when(userService.getNum()).thenReturn(3L);
        when(userService.getPage(1)).thenReturn(userPages);

        mvc.perform(get("/users/pages/1").with(csrf()).accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(model().attribute("userNum", is(3L)))
            .andExpect(model().attribute("userPages", is(userPages)))
            .andExpect(model().attribute("beginIndex", is(1)))
            .andExpect(model().attribute("currentIndex", is(1)))
            .andExpect(model().attribute("endIndex", is(2)))
            .andExpect(view().name("user_list"));
    }

    @Test
    public void testNoUsers() throws Exception {
        when(userService.getNum()).thenReturn(0L);
        when(userService.getPage(1)).thenThrow(new EmptyUserListException("Dummy"));

        mvc.perform(get("/users/pages/1").with(csrf()).accept(MediaType.TEXT_HTML))
            .andExpect(status().isNotFound())
            .andExpect(model().attributeDoesNotExist("userPages"))
            .andExpect(model().attributeExists("emptyDB"))
            .andExpect(view().name("error"));
    }
}
