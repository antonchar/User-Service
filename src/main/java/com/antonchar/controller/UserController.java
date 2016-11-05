package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String query) {
        logger.info("POST: Delete user with id = " + id);
        logRequestSender(page, query);

        userService.deleteUser(id);

        return "redirect:/users" + (page != null ? "/pages/" + page : "/search?query=" + query);
    }

    private void logRequestSender(Integer page, String query) {
        if (page != null) {
            logger.info("Delete request from the user list view");
        } else if (query != null) {
            logger.info("Delete request from the search view");
        } else {
            logger.warn("Unknown delete request sender");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long id, Model model) {
        logger.info("GET: Show data for user with id = " + id);

        User user = userService.findUser(id);

        model.addAttribute("user", user);

        return "user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result,
                           @RequestParam(required = false) String state, Model model) {
        logger.info("POST: Edit user");

        if (state != null && state.equals("init")) {
            logger.info("Show user data to edit: " + user);
            return "user_edit";
        }
        if (result.hasErrors()) {
            logger.error("Invalid user edit data: " + user);
            return "user_edit";
        }

        userService.saveUser(user);
        logger.info("User data updated successfully! " + user);

        model.addAttribute("user", user);
        model.addAttribute("saved", true);

        return showUser(user.getId(), model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddUserForm() {
        logger.info("GET: Add new user page");

        return "user_add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, Model model) {
        logger.info("POST: Add new user");

        if (result.hasErrors()) {
            logger.error("Invalid new user data: " + user);
            return "user_add";
        }

        user.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
        User savedUser = userService.addUser(user);
        logger.info("New user saved successfully! " + user);

        model.addAttribute("user", savedUser);
        model.addAttribute("saved", true);

        return showUser(user.getId(), model);
    }
}
