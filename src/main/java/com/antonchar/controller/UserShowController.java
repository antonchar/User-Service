package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserShowController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showUsers() {
        List<User> users = userService.getUsers();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("users", users);
        return mav;
    }
}
