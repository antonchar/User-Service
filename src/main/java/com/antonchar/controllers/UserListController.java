package com.antonchar.controllers;

import com.antonchar.entities.User;
import com.antonchar.exceptions.NoUsersException;
import com.antonchar.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/users")
@SessionAttributes("userNum")
public class UserListController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String showUserPage(@PathVariable Integer pageNumber, Model model) {
        log.info("GET: Show user page number " + pageNumber);

        Page<User> userPages = userService.getUsers(pageNumber);
        int currentIndex = userPages.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 5);
        int endIndex = Math.min(beginIndex + 10, userPages.getTotalPages());

        model.addAttribute("userPages", userPages);
        model.addAttribute("beginIndex", beginIndex);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("endIndex", endIndex);

        return "users";
    }

    @ModelAttribute("userNum")
    public long getUserNumber(){
        long userNum = userService.getUserNum();
        log.debug("List of users accessed : " + userNum + " user(s) found.");

        return userNum;
    }

    @ExceptionHandler
    public String emptyDbHandler(NoUsersException e, Model model) {
        model.addAttribute("emptyDB", e.getMessage());

        return "error";
    }
}
