package com.koffe.koffe.controller;

import com.koffe.koffe.model.*;
import com.koffe.koffe.repository.IOrderItemsRepository;
import com.koffe.koffe.service.*;
import com.koffe.koffe.service.serviceIMPL.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    ICartService cartService = new CartServiceIMPL();
    IAddressService addressService = new AddressServiceIMPL();

    IOrderService orderService = new OrderServiceIMPL();

    IOrderItemsService orderItemService = new OrderItemsServiceIMPL();
    IProductService productService = new ProductServiceIMPL();



    @PostMapping("/order")
    public String doOrder(HttpSession session,
                          @RequestParam("address") String addressInput,
                          @RequestParam("total") String totalInput,
                          Model model) {
        int total = Integer.parseInt(totalInput);
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = cartService.findCartByUserId(user.getUserId());
        boolean isContainNotSaleProduct = true;
        for (Cart cart: cartList) {
            boolean isOnSaleProduct = productService.isOnSale(cart.getProductId());
            if (!isOnSaleProduct){
                cartService.deleteById(cart.getCartId());
                isContainNotSaleProduct = false;
            };
        }

        if (!isContainNotSaleProduct) {
            model.addAttribute("user", user);
            List<CartDetail> userCart = cartService.findAllCartDetailByUserId(user.getUserId());
            int totalPrice = cartService.getTotalPriceOfUserId(user.getUserId());
            Address address = addressService.findAllAddressByUserId(user.getUserId());
            model.addAttribute("userCart", userCart);
            model.addAttribute("userAddress", address);
            model.addAttribute("total", totalPrice);
            model.addAttribute("errorMessage", 400);
            return "/user/pages/cart";
        }

        Address address = addressService.findAllAddressByUserId(user.getUserId());
        int addressId;

        if (address == null) {
            addressService.save(new Address(addressInput, user.getUserId()));
            addressId = addressService.getLastAddressId();
        } else {
            addressService.update(new Address(address.getAddressId(), addressInput));
            addressId = address.getAddressId();
        }

        orderService.save(new Order(user.getUserId(), addressId, total));
        int orderId = orderService.getLastOrderId();
        orderItemService.saveListCartToOrderItem(orderId, cartList);
        cartService.deleteCartByUserId(user.getUserId());
        return "redirect:/";
    }

    @PostMapping("/admin/orderUpdateStatus")
    public String updateAdminOrderStatus(@RequestParam("orderId") int orderId) {
        orderService.updateStatusOrderByOrderId(orderId);
        return "redirect:/admin/orderMNG";
    }
}
