package com.koffe.koffe.controller;


import com.koffe.koffe.model.CartDetail;
import com.koffe.koffe.model.Comments;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.ICommentsService;
import com.koffe.koffe.service.serviceIMPL.CommentsServiceIMPL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentsController {
    ICommentsService commentsService = new CommentsServiceIMPL();


    @PostMapping("/about")
    public String doComments(@RequestParam("fullName") String fullName,
                             @RequestParam("email") String email,
                             @RequestParam("subject") String subject,
                             @RequestParam("message") String message,
                             HttpSession session,
                             Model model) {
        int wrongCode = 0;
        boolean isMatchesFullName = commentsService.isMatchesFullName(fullName);
        boolean isMatchesEmail = commentsService.isMatchesEmail(email);
        if (!isMatchesFullName) {
            wrongCode = 400;
        } else if (!isMatchesEmail) {
            wrongCode = 401;
        }

        if (wrongCode != 0){
            model.addAttribute("wrongCode", wrongCode);
        } else {
            LocalDateTime now = LocalDateTime.now();
            Timestamp currTimestamp = Timestamp.valueOf(now);
            commentsService.save(new Comments(fullName, email, subject, message, currTimestamp));
            model.addAttribute("successMessage", "OK");
        }

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<CartDetail> userCart = new ArrayList<>();
        model.addAttribute("userCart", userCart);

        return "/user/pages/aboutComments";
    }
}
