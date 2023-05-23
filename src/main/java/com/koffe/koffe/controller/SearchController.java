package com.koffe.koffe.controller;


import com.koffe.koffe.model.*;
import com.koffe.koffe.service.*;
import com.koffe.koffe.service.serviceIMPL.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    IUserService userService = new UserServiceIMPL();
    IOrderService orderService = new OrderServiceIMPL();
    IBookingTableService bookService = new BookingTableServiceIMPL();
    IProductService productService = new ProductServiceIMPL();
    ICommentsService commentService = new CommentsServiceIMPL();



    @PostMapping("/admin/search")
    public String doSearching(@RequestParam("searchValue") String searchValue,
                              @RequestParam("currentUrl") String currentUrl,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("searchValue", searchValue);

        if (currentUrl.contains("userMNG")) {
            return "redirect:/admin/userMNG/search";

        } else if (currentUrl.contains("billingMNG")) {
            return "redirect:/admin/billingMNG/search";

        } else if (currentUrl.contains("productMNG")) {
            return "redirect:/admin/productMNG/search";

        }
        return "redirect:/admin/commentMNG/search";
    }

    @GetMapping("/admin/userMNG/search")
    public String showUserSearch(Model model,
                                 @ModelAttribute("searchValue") String searchValue,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }

        List<User> userList = userService.findUserByName(searchValue);
        model.addAttribute(userList);
        model.addAttribute("currentUser", user);
        return "/admin/pages/userMNG";
    }

    @GetMapping("/admin/billingMNG/search")
    public String showOrderSearch(Model model,
                                  @ModelAttribute("searchValue") String searchValue,
                                  HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        boolean isNumber = orderService.isNumber(searchValue);
        if (isNumber) {
            Order order = orderService.findAcceptedOrderById(Integer.parseInt(searchValue));
            List<Order> orderList = new ArrayList<>();
            List<Integer> orderSize = new ArrayList<>();
            if (order != null) {
                orderList.add(order);
                orderSize = orderService.getAllOrderSize(orderList);
            }
            model.addAttribute("orderSize", orderSize);
            model.addAttribute("orderList", orderList);
        } else {
            List<BookingTable> bookingTable = bookService.findAcceptedBookingTableByUserName(searchValue);
            model.addAttribute("bookingTable", bookingTable);
        }
        return "/admin/pages/billingMNG";
    }

    @GetMapping("/admin/productMNG/search")
    public String showSearchProduct(Model model,
                                    @ModelAttribute("searchValue") String searchValue,
                                    HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Product> products = productService.findProductByName(searchValue);
        model.addAttribute("products", products);
        return "/admin/pages/productMNG";
    }

    @GetMapping("/admin/commentMNG/search")
    public String showSearchComment(Model model,
                                    @ModelAttribute("searchValue") String searchValue,
                                    HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Comments> comments = commentService.findAllCommentByCommentsFullName(searchValue);
        model.addAttribute("comments", comments);
        return "/admin/pages/commentMNG";
    }

}
