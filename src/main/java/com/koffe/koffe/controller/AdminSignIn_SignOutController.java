package com.koffe.koffe.controller;


import com.koffe.koffe.model.User;
import com.koffe.koffe.service.IUserService;
import com.koffe.koffe.service.serviceIMPL.UserServiceIMPL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminSignIn_SignOutController {

    IUserService userService = new UserServiceIMPL();

    @PostMapping("/admin/signin")
    public String signInAdmin(@RequestParam("phoneNumber") String phoneNumber,
                              @RequestParam("password") String password,
                              HttpServletRequest request,
                              Model model) {
        User user =  userService.findUserByPhoneNumberAndPassword(phoneNumber, password);

        int wrongCode = 0;
        if (user == null) {
            wrongCode = 400;
        } else if (!user.isStatus()) {
            wrongCode = 401;
        } else if (user.getRole() == 4 || user.getRole() == 3) {
            wrongCode = 402;
        }

        if (wrongCode != 0) {
            model.addAttribute("wrongCode", wrongCode);
            return "/admin/pages/signin";
        } else {
            request.getSession().setAttribute("user", user);
            if (user.getRole() == 1)
                return "redirect:/admin/dashboard";
            else
                return "redirect:/admin/userMNG";
        }

    }

}
