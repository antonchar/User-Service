package com.antonchar.userservice.controllers;

import com.antonchar.userservice.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserListController.class)
public class UserListControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testList() throws Exception {
        when(userService.getNum()).thenReturn(2L);

        mvc.perform(get("/pages/1").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotFound());
                //.andExpect(model().attribute("userNum", is(2L)));
    }
}
