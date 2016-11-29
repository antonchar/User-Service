package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
import com.antonchar.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;

@Slf4j
@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userNum", "existingUser", "newUser"})
public class UserCrUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long id, Model model, @RequestParam(required = false) boolean saved) {
        log.info("GET: Show data for user with id = " + id);

        User user = userService.findUser(id);

        model.addAttribute("existingUser", user);
        model.addAttribute("saved", saved);

        return "user";
    }

    // CREATE

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {
        log.info("GET: Add new user page");

        model.addAttribute("newUser", new User());

        return "user_add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newUser") @Valid User user, BindingResult result,
                          SessionStatus sessionStatus) {
        log.info("POST: Add new user");

        validator.validate(user, result);

        if (result.hasErrors()) {
            log.error("Invalid new user data: " + user);
            return "user_add";
        }

        user.setCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
        User savedUser = userService.addUser(user);

        sessionStatus.setComplete();

        log.info("New user saved successfully! " + user);

        return String.format("redirect:/user/%d?saved=true", savedUser.getId());
    }

    //UPDATE

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("existingUser") @Valid User user, BindingResult result,
                           @RequestParam(required = false) String state, SessionStatus sessionStatus) {
        log.info("POST: Edit user");

        validator.validate(user, result);

        if (state != null && state.equals("init")) {
            log.info("Show user data to edit: " + user);
            return "user_edit";
        }
        if (result.hasErrors()) {
            log.error("Invalid user edit data: " + user);
            return "user_edit";
        }

        userService.saveUser(user);

        sessionStatus.setComplete();

        log.info("User data updated successfully! " + user);

        return String.format("redirect:/user/%d?saved=true", user.getId());
    }

    // DELETE

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String query, SessionStatus sessionStatus) {
        log.info("POST: Delete user with id = " + id);
        logRequestSender(page, query);

        userService.deleteUser(id);
        sessionStatus.setComplete();

        return "redirect:/users" + (page != null ? "/pages/" + page : "/search?query=" + query);
    }

    private void logRequestSender(Integer page, String query) {
        if (page != null) {
            log.info("Delete request from the user list view");
        } else if (query != null) {
            log.info("Delete request from the search view");
        } else {
            log.warn("Unknown delete request sender");
        }
    }
}
