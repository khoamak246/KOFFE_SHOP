package com.koffe.koffe.controller;


import com.koffe.koffe.model.*;
import com.koffe.koffe.service.*;
import com.koffe.koffe.service.serviceIMPL.*;
import com.koffe.koffe.utils.HideInfo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserRouterController {

    IProductService productService = new ProductServiceIMPL();
    ICartService cartService = new CartServiceIMPL();
    IAddressService addressService = new AddressServiceIMPL();
    IOrderService orderService = new OrderServiceIMPL();
    IOrderItemsService orderItemService = new OrderItemsServiceIMPL();

    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Product> productList = productService.findAllProductsBySize(1);
        List<ProductDetail> productDetailList = new ArrayList<>();
        for (Product product: productList) {
            boolean isOnSaleProduct = productService.isOnSale(product.getProductId());
            if (isOnSaleProduct) {
                List<Product> products = productService.findByIdProduct(product.getProductId());
                if (!products.isEmpty()){
                    int productId = products.get(0).getProductId();
                    String name = products.get(0).getProductName();
                    String description = products.get(0).getProductDescription();
                    String avatar = products.get(0).getProductAvatar();
                    int priceS = products.get(0).getPrice();
                    int priceM = products.get(1).getPrice();
                    int priceL = products.get(2).getPrice();
                    ProductDetail tempProductDetail = new ProductDetail(productId, name, description, avatar, priceS, priceM, priceL);
                    productDetailList.add(tempProductDetail);
                }
            }
        }

        model.addAttribute("user", user);
//        model.addAttribute("product", productList);
        model.addAttribute("product", productDetailList);
        List<CartDetail> userCart = new ArrayList<>();
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
        }

        model.addAttribute("userCart", userCart);
        return "/user/index";
    }

    @GetMapping("/about")
    public String showAboutPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<CartDetail> userCart = new ArrayList<>();
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
        }

        model.addAttribute("userCart", userCart);
        return "/user/pages/about";
    }

    @GetMapping("/menu")
    public String showMenuPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Product> productList = productService.findAllProductsBySize(1);
        List<ProductDetail> productDetailList = new ArrayList<>();
        for (Product product: productList) {
            boolean isOnSaleProduct = productService.isOnSale(product.getProductId());
            if (isOnSaleProduct) {
                List<Product> products = productService.findByIdProduct(product.getProductId());
                if (!products.isEmpty()){
                    int productId = products.get(0).getProductId();
                    String name = products.get(0).getProductName();
                    String description = products.get(0).getProductDescription();
                    String avatar = products.get(0).getProductAvatar();
                    int priceS = products.get(0).getPrice();
                    int priceM = products.get(1).getPrice();
                    int priceL = products.get(2).getPrice();
                    ProductDetail tempProductDetail = new ProductDetail(productId, name, description, avatar, priceS, priceM, priceL);
                    productDetailList.add(tempProductDetail);
                }
            }
        }

        model.addAttribute("user", user);
//        model.addAttribute("product", productList);
        model.addAttribute("product", productDetailList);
        List<CartDetail> userCart = new ArrayList<>();
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
        }

        model.addAttribute("userCart", userCart);
        return "/user/pages/menu";
    }

    @GetMapping("/reservation")
    public String showReservationPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<CartDetail> userCart = new ArrayList<>();
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
        }

        model.addAttribute("userCart", userCart);
        return "/user/pages/reservation";
    }

    @GetMapping("/signin")
    public String showSignInPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "/user/pages/signin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/user/pages/signup";
    }

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("userCart");
        return "redirect:/signin";
    }

    @GetMapping("/cart")
    public String showCart(Model model,
                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<CartDetail> userCart = new ArrayList<>();
        int totalPrice = 0;
        Address address = null;
        if (user != null) {
            userCart = cartService.findAllCartDetailByUserId(user.getUserId());
            totalPrice = cartService.getTotalPriceOfUserId(user.getUserId());
            address = addressService.findAllAddressByUserId(user.getUserId());
        } else {
            return "redirect:/";
        }
        model.addAttribute("userCart", userCart);
        model.addAttribute("userAddress", address);
        model.addAttribute("total", totalPrice);

        return "/user/pages/cart";
    }

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
            List<Order> orderList = orderService.findAllOrderByUserId(user.getUserId());
            Address address = addressService.findAllAddressByUserId(user.getUserId());
            model.addAttribute("hidePassword", HideInfo.hideInfoTypePassword(user.getPhoneNumber()));
            model.addAttribute("orderList", orderList);
            model.addAttribute("address", address);
            return "/user/pages/profile";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/orderDetail")
    public String showOrderDetail(@RequestParam("orderId") int orderId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<OrderItemsDetail> orderItems = orderItemService.findOrderItemsDetailByOrderId(orderId);
            Order order = orderService.findById(orderId);
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("user", user);
            model.addAttribute("oder", order);
            return "/user/pages/orderDetail";
        } else {
            return "redirect:/";
        }
    }
}
