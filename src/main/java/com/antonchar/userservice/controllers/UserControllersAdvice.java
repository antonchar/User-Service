package com.antonchar.userservice.controllers;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class UserControllersAdvice {

    @InitBinder("existingUser")
    public void initUserBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id", "creationDate");
    }
}
