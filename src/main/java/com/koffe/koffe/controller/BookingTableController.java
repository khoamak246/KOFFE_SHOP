package com.koffe.koffe.controller;


import com.koffe.koffe.model.BookingTable;
import com.koffe.koffe.model.CartDetail;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.IBookingTableService;
import com.koffe.koffe.service.ICartService;
import com.koffe.koffe.service.serviceIMPL.BookingTableServiceIMPL;

import com.koffe.koffe.service.serviceIMPL.CartServiceIMPL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingTableController {
    IBookingTableService bookingTableService = new BookingTableServiceIMPL();
    ICartService cartService = new CartServiceIMPL();

    @PostMapping("/reservation")
    public String doReservation(@RequestParam("fullName") String name,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("date") String date,
                                @RequestParam("time") String time,
                                @RequestParam("amountPeople") String amountPeople,
                                Model model, HttpSession session) {
        int wrongCode = 0;
        String dateTimeFormatString = bookingTableService.handleInputDateTime(date, time);
        Timestamp bookingDateTimeStamp = bookingTableService.getTimeStampFromInput(dateTimeFormatString);
        int amountP = Integer.parseInt(String.valueOf(amountPeople));
        boolean isMatchesFullName = bookingTableService.isMatchesFullName(name);
        boolean isMatchesPhoneNumber = bookingTableService.isMatchesPhoneNumber(phoneNumber);
        boolean isBookingDateAfterCurrentDate = bookingTableService.isBookingDateAfterCurrentDate(dateTimeFormatString);
        boolean isBookingDateInLimitDateBookingTimeDistance = bookingTableService.isBookingDateInLimitDateBookingTimeDistance(dateTimeFormatString);
        boolean isBookingDateAfterTimeLimitDistance = bookingTableService.isBookingDateAfterTimeLimitDistance(dateTimeFormatString);

        if (!isMatchesFullName){
            wrongCode = 400;
        } else if (!isMatchesPhoneNumber) {
            wrongCode = 401;
        } else if (!isBookingDateAfterCurrentDate || !isBookingDateInLimitDateBookingTimeDistance || !isBookingDateAfterTimeLimitDistance) {
            wrongCode = 402;
        }

        if (wrongCode != 0){
            model.addAttribute("wrongCode", wrongCode);
        } else {
            bookingTableService.save(new BookingTable(bookingDateTimeStamp, amountP, phoneNumber, name));
            model.addAttribute("successMessage", "OK");
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<CartDetail> userCart = new ArrayList<>();
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
        }

        model.addAttribute("userCart", userCart);

        return "/user/pages/reservation";
    }


    @PostMapping("/admin/updateBookingTalbeStatus")
    public String updateBookingTableStatus(@RequestParam("bookingTableId") int bookingTableId){
        bookingTableService.updateById(bookingTableId);
        return "redirect:/admin/orderMNG";
    }
}
