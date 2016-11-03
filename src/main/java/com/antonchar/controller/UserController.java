package com.antonchar.controller;

import com.antonchar.entity.User;
import com.antonchar.service.UserService;
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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result,
                           @RequestParam(required = false) String state, Model model) {
        if (state != null && state.equals("init")) {
            return "user_edit";
        }
        if (result.hasErrors()) {
            return "user_edit";
        }
        userService.saveUser(user);
        model.addAttribute("user", user);
        model.addAttribute("saved", true);
        return showUser(user.getId(), model);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddUserForm() {
        return "user_add_form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_add";
        }
        user.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
        User savedUser = userService.addUser(user);
        model.addAttribute("user", savedUser);
        model.addAttribute("saved", true);
        return showUser(user.getId(), model);
    }
}
