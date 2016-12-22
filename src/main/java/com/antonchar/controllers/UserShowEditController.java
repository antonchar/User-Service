package com.antonchar.controllers;

import com.antonchar.services.UserService;
import com.antonchar.services.dto.UserDto;
import com.antonchar.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
@SessionAttributes("existingUser")
public class UserShowEditController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long id, Model model, @RequestParam(required = false) boolean saved) {
        log.info("GET: Show data for user with id = " + id);
        UserDto user = userService.findUser(id);

        model.addAttribute("existingUser", user);
        model.addAttribute("saved", saved);
        return "user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("existingUser") @Valid UserDto user, BindingResult result,
                           @RequestParam(required = false) String state, SessionStatus sessionStatus) {
        log.info("POST: Edit user");
        new UserValidator().validate(user, result);

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
}
