package com.antonchar.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class ControllerBindings {

    @InitBinder("existingUser")
    public void initUserBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id", "creationDate");
    }
}
