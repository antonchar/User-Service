package com.antonchar.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StartController {

    private static Logger logger = Logger.getLogger(StartController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showStartPage() {
        logger.info("GET: Show home page");

        return "index";
    }
}
