package com.antonchar.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String showLoginPage(@RequestParam(required = false) String error) {
        return "login";
    }
}
