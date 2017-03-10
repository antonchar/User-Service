package com.antonchar.userservice.controllers;

import com.antonchar.userservice.entities.User;
import com.antonchar.userservice.services.UserService;
import com.antonchar.userservice.services.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('SUPERADMIN')")
@RequestMapping("/user")
@SessionAttributes(value = {"userNum", "newUser"})
public class UserCreateDeleteController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userValidator")
    private Validator userValidator;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(value = "/add")
    public String addUserStarter(Model model) {
        log.info("GET: Add new user page");
        model.addAttribute("newUser", new UserDto());
        return "user_add_starter";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("newUser") @Valid UserDto user, BindingResult result,
                          SessionStatus sessionStatus) {
        log.info("POST: Add new user");

        if (!result.hasFieldErrors("email")) {
            userService.findByEmail(user.getEmail())
                .ifPresent(usr -> result.rejectValue("email", "error.email.taken"));
        }

        userValidator.validate(user, result);

        if (result.hasErrors()) {
            log.error("Invalid new user data: {}", user);
            return "user_add_validated";
        }

        user
            .setPwdHash(encoder.encode("123"))
            .setRole(User.Role.USER)
            .setBlocked(false)
            .setCreationDate(LocalDateTime.now());

        UserDto savedUser = userService.save(user);
        sessionStatus.setComplete();
        log.info("New user saved successfully! {}", user);

        return String.format("redirect:/user/%d?saved=true", savedUser.getId());
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id, @RequestParam(required = false) Integer page,
                             @RequestParam(required = false) String query, SessionStatus sessionStatus) {
        log.info("POST: Delete user with id = {}", id);
        logRequestSender(page, query);

        userService.delete(id);
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
