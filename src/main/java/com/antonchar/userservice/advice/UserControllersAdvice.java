package com.antonchar.userservice.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.antonchar.userservice.exception.EmptyUserListException;

@ControllerAdvice
public class UserControllersAdvice {

    @InitBinder("existingUser")
    public void initUserBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id", "email", "pwdHash", "creationDate");
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyUserListException.class)
    public String emptyDbHandler(Exception e, Model model) {
        model.addAttribute("emptyDB", e.getMessage());
        return "error";
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedHandler(Exception e, Model model) {
        model.addAttribute("accessDenied", e.getMessage());
        return "error";
    }

    @Order
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String genericHandler(Exception e, Model model) {
        model.addAttribute("genericError", e.getMessage());
        return "error";
    }
}
