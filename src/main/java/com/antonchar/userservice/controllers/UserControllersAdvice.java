package com.antonchar.userservice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllersAdvice {

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication auth) {
        return auth == null ? null : (UserDetails) auth.getPrincipal();
    }

    @InitBinder("existingUser")
    public void initUserBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id", "creationDate");
    }
}
