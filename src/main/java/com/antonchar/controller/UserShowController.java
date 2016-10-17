package com.antonchar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserShowController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showUsers() {
        return new ModelAndView("index");
    }
}
