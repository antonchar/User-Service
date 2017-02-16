package com.antonchar.userservice.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;
import com.antonchar.userservice.util.exceptions.EmptyUserListException;

@Slf4j
@Controller
@RequestMapping("/users")
@SessionAttributes("userNum")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/pages/{pageNumber}")
    public String showUserPage(@PathVariable Integer pageNumber, Model model) {
        log.info("GET: Show user page number {}", pageNumber);
        Page<UserDto> userPages = userService.getPage(pageNumber);

        int currentIndex = userPages.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 5);
        int endIndex = Math.min(beginIndex + 10, userPages.getTotalPages());

        model.addAttribute("userPages", userPages);
        model.addAttribute("beginIndex", beginIndex);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("endIndex", endIndex);

        return "users";
    }

    @ModelAttribute("userNum")
    public long getUserNumber(){
        Long userNum = userService.getNum();
        log.debug("List of users accessed : {} user(s) found.", userNum);
        return userNum;
    }

    @ExceptionHandler(EmptyUserListException.class)
    public String emptyDbHandler(EmptyUserListException e, Model model) {
        model.addAttribute("emptyDB", e.getMessage());
        return "error";
    }
}
