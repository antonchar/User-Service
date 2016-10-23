package com.antonchar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String showStartPage() {
        return "index";
    }
}
