package com.antonchar.userservice.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.antonchar.userservice.config.SecurityConfig;
import com.antonchar.userservice.config.TestConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
@Import(value = {SecurityConfig.class, TestConfig.class})
public class LoginControllerMvcTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testLogin() throws Exception {
        mvc.perform(get("/login").accept(MediaType.TEXT_HTML))
            .andExpect(status().isOk())
            .andExpect(view().name("login"));
    }
}
