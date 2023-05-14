package com.koffe.koffe.controller;

import com.koffe.koffe.model.User;
import com.koffe.koffe.service.IUserService;
import com.koffe.koffe.service.serviceIMPL.UserServiceIMPL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
    IUserService userService = new UserServiceIMPL();

    @PostMapping("/signup")
    public String signupNewMember(@ModelAttribute("user") User user, Model model) {
        boolean isMatchesFullName = userService.checkMatchesFullName(user.getFullName());
        boolean isMatchesPhoneNumber = userService.checkMatchesPhoneNumber(user.getPhoneNumber());
        boolean isMatchesPassword = userService.checkMatchesPassword(user.getPassword());
        boolean isExistPhoneNumber = userService.checkExistUserByPhoneNumber(user.getPhoneNumber());
        int wrongCode = 0;
        if (!isMatchesFullName) {
            wrongCode = 400;
        } else if (!isMatchesPhoneNumber) {
            wrongCode = 401;
        } else if (!isMatchesPassword) {
            wrongCode = 402;
        } else if (isExistPhoneNumber) {
            wrongCode = 403;
        }

        if (wrongCode != 0) {
            model.addAttribute("wrongCode", wrongCode);
            return "/user/pages/signup";
        } else {
            userService.save(user);
            return "redirect:/signin";
        }
    }

    @PostMapping("/signin")
    public String signInMember(@RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("password") String password,
                               Model model,
                               HttpServletRequest request) {
        User user = userService.findUserByPhoneNumberAndPassword(phoneNumber, password);
        int wrongCode = 0;
        if (user == null) {
            wrongCode = 400;
        } else if (!user.isStatus()) {
            wrongCode = 401;
        }

        if (wrongCode != 0) {
            model.addAttribute("wrongCode", wrongCode);
            return "/user/pages/signin";
        } else {
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
    }

    @PostMapping("/admin/user/updateUserInfo")
    public String updateUserInfo(@RequestParam("roleSelect") int roleSelect,
                                 @RequestParam("statusSelect") int statusSelect,
                                 @RequestParam("userId") int userId) {
        User user = userService.findById(userId);
        user.setRole(roleSelect);
        user.setStatus(statusSelect == 1);
        userService.update(user);
        return "redirect:/admin/userMNG";
    }
}
