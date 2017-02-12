package com.antonchar.userservice.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    public String showStartPage() {
        log.info("GET: Show home page");
        return "index";
    }
}
