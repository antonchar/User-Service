package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String showUserPage(@PathVariable Integer pageNumber, Model model) {
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
        return "user_search";
    }
}
