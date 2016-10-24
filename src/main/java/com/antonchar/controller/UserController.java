package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, @RequestParam Integer page) {
        userService.deleteUser(id);
        return "redirect:/users/pages/" + page;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long id, Model model) {
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "user";
    }
}
