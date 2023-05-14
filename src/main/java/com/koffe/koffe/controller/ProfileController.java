package com.koffe.koffe.controller;


import com.koffe.koffe.model.Address;
import com.koffe.koffe.model.Order;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.IAddressService;
import com.koffe.koffe.service.IOrderService;
import com.koffe.koffe.service.IUserService;
import com.koffe.koffe.service.serviceIMPL.AddressServiceIMPL;
import com.koffe.koffe.service.serviceIMPL.OrderServiceIMPL;
import com.koffe.koffe.service.serviceIMPL.UserServiceIMPL;
import com.koffe.koffe.utils.HideInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:upload_file.properties")
public class ProfileController {
    @Value("${file-upload}")
    private String fileUpload;
    IUserService userService = new UserServiceIMPL();
    IAddressService addressService = new AddressServiceIMPL();
    IOrderService orderService = new OrderServiceIMPL();

    @PostMapping("/profile/changeName")
    public String updateProfileName(@RequestParam("newName") String newName, HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setFullName(newName);
        userService.update(user);
        return "redirect:/profile";
    }

    @PostMapping("/profile/changePhoneNumber")
    public String updateProfilePhoneNumber(@RequestParam("newPhoneNumber") String newPhoneNumber,
                                           HttpSession session, Model model) {
        boolean isExistPhoneNumber = userService.checkExistUserByPhoneNumber(newPhoneNumber);
        if (isExistPhoneNumber) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("user", user);
            List<Order> orderList = orderService.findAllOrderByUserId(user.getUserId());
            Address address = addressService.findAllAddressByUserId(user.getUserId());
            model.addAttribute("hidePassword", HideInfo.hideInfoTypePassword(user.getPhoneNumber()));
            model.addAttribute("orderList", orderList);
            model.addAttribute("address", address);
            model.addAttribute("errorMessage", "OOP! Your phone number had been exist!");
            return "/user/pages/profile";
        } else {
            User user = (User) session.getAttribute("user");
            user.setPhoneNumber(newPhoneNumber);
            userService.update(user);
            session.removeAttribute("user");
            return "redirect:/signin";
        }
    }

    @PostMapping("/profile/changeAddress")
    public String updateProfileAddress(@RequestParam("newAddress") String newAddress, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Address address = addressService.findAllAddressByUserId(user.getUserId());
        if (address != null) {
            address.setLocation(newAddress);
            addressService.update(address);
        } else {
            address = new Address(newAddress, user.getUserId());
            addressService.save(address);
        }

        return "redirect:/profile";
    }

    @PostMapping("/profile/saveImg")
    public String uploadAvatar(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        File currentAvatar = new File(fileUpload + user.getAvatar());
        currentAvatar.delete();

        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        user.setAvatar(fileName);
        userService.update(user);
        return "redirect:/profile";
    }


}
