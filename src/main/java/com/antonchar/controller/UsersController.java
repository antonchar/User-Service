package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.exception.EmptyUsersException;
import com.antonchar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String showUserPage(@PathVariable Integer pageNumber, Model model) {
        log.info("GET: Show user page number " + pageNumber);

        Page<User> userPages = userService.getUsers(pageNumber);
        int currentIndex = userPages.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 5);
        int endIndex = Math.min(beginIndex + 10, userPages.getTotalPages());

        model.addAttribute("userPages", userPages);
        model.addAttribute("beginIndex", beginIndex);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("endIndex", endIndex);

        return "users";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUser(@RequestParam(required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            log.info("GET: Search user by name containing '" + query + "'");

            List<User> users = userService.findUsers(query);

            model.addAttribute("users", users);
            model.addAttribute("query", query);
        } else {
            log.info("GET: Search user page");
        }

        return "user_search";
    }

    @ExceptionHandler
    public String emptyDbHandler(EmptyUsersException e, Model model) {
        model.addAttribute("emptyDB", e.getMessage());
        return "error";
    }
}
