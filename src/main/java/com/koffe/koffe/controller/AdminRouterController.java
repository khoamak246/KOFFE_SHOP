package com.koffe.koffe.controller;


import com.koffe.koffe.model.*;
import com.koffe.koffe.service.*;
import com.koffe.koffe.service.serviceIMPL.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminRouterController {

    IUserService userService = new UserServiceIMPL();
    IOrderService orderService = new OrderServiceIMPL();
    IOrderItemsService orderItemsService = new OrderItemsServiceIMPL();
    IBookingTableService bookService = new BookingTableServiceIMPL();
    IProductService productService = new ProductServiceIMPL();
    ICommentsService commentsService = new CommentsServiceIMPL();

    @GetMapping({"/admin", "/admin/signin"})
    public String showAdminSignInPage(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");

        if (user != null && user.getRole() != 4 && user.getRole() != 3) {
            return "redirect:/admin/dashboard";
        }
        String searchValue = request.getParameter("searchValue");
        if (searchValue != null && searchValue.length() == 0) {
            return "redirect:/admin/signin";
        }
        return "/admin/pages/signin";
    }

    @GetMapping("/admin/signout")
    public String signOutAdmin(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin/signin";
    }

    @GetMapping("/admin/dashboard")
    public String showAdminHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        if (user.getRole() == 2) {
            return "redirect:/admin/userMNG";
        }
        model.addAttribute("currentUser", user);
        List<User> userList = userService.findAll();
        model.addAttribute(userList);
        int totalUser = userService.getCountOfUser();
        int totalOrder = orderService.getCountOfOrder();
        int totalReservation = bookService.getCountOfBookTable();
        int totalSales = orderService.getSumTotalOrder();
        List<Order_Amount_Product> orderProductList = orderService.getOrderAmountProduct();
        List<Integer> monthSale = orderService.getTotalEachMonthInYear();
        List<Integer> comments = commentsService.getTotalCommentInYear();
        model.addAttribute("dashboardData", new Dashboard_Data(
                totalUser,
                totalOrder,
                totalReservation,
                totalSales,
                orderProductList,
                monthSale,
                comments));
        return "/admin/index";
    }

    @GetMapping("/admin/userMNG")
    public String showAdminUserMNGPage(HttpSession session, Model model) {
        //TODO: Block role customer, staff user
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }

        List<User> userList = userService.findAll();
        model.addAttribute(userList);
        model.addAttribute("currentUser", user);
        return "/admin/pages/userMNG";
    }

    @GetMapping("/admin/billingMNG")
    public String showAdminBillingMNGPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Order> orders = orderService.findAll();
        List<Integer> orderSize = orderService.getAllOrderSize(orders);
        List<BookingTable> bookingTable = bookService.findAll();
        model.addAttribute("orderList", orders);
        model.addAttribute("orderSize", orderSize);
        model.addAttribute("bookingTable", bookingTable);
        return "/admin/pages/billingMNG";
    }

    @GetMapping("/admin/orderMNG")
    public String showOrderMGNPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Order> orders = orderService.findAll();
        List<Integer> orderSize = orderService.getAllOrderSize(orders);
        List<BookingTable> bookingTable = bookService.findAll();
        model.addAttribute("orderList", orders);
        model.addAttribute("orderSize", orderSize);
        model.addAttribute("bookingTable", bookingTable);
        return "/admin/pages/orderMNG";
    }


    @GetMapping("/admin/orderDetailMNG")
    public String showOrderDetailMGNPage(HttpServletRequest request,
                                         Model model,
                                         HttpSession session) {

        int orderId = 0;
        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam != null) {
            orderId = Integer.parseInt(orderIdParam);
        }
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }

        model.addAttribute("currentUser", user);
        List<OrderItemsDetail> orderItems = orderItemsService.findOrderItemsDetailByOrderId(orderId);
        Order order = orderService.findById(orderId);
        User orderUser = userService.findById(order.getUserId());
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("user", orderUser);
        model.addAttribute("oder", order);

        String backPage;
        if (!order.isStatus())
            backPage = "orderMNG";
        else
            backPage = "billingMNG";

        model.addAttribute("backPage", backPage);

        return "/admin/pages/adminOrderDetailMGN";
    }

    @GetMapping("/admin/productMNG")
    public String showProductMGNPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/admin/pages/productMNG";
    }

    @GetMapping("/admin/commentMNG")
    public String showCommentMNGPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() == 4 || user.getRole() == 3) {
            return "redirect:/admin/signin";
        }
        model.addAttribute("currentUser", user);
        List<Comments> comments = commentsService.findAll();
        model.addAttribute("comments", comments);
        return "/admin/pages/commentMNG";
    }
}
