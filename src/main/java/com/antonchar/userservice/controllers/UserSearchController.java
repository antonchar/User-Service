package com.antonchar.userservice.controllers;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserSearchController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/search")
    public String searchUser(@RequestParam(required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            log.info("GET: Search user by name containing '{}'", query);
            List<UserDto> users = userService.findByName(query);

            model.addAttribute("users", users);
            model.addAttribute("query", query);

        } else {
            log.info("GET: Search user page");
        }

        return "user_search";
    }
}
