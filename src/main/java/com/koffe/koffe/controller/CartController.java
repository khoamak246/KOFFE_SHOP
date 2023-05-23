package com.koffe.koffe.controller;


import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.ICartService;
import com.koffe.koffe.service.serviceIMPL.CartServiceIMPL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    ICartService cartService = new CartServiceIMPL();

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") int productId,
                            @RequestParam("size") int sizeId,
                            @RequestParam("quantity") int quantity,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        Cart cart = cartService.findCartByUserIdAndProductIdAndSizeId(userId, productId, sizeId);
        if (cart != null) {
            cartService.updatePlusQuantityToCart(userId, productId, sizeId, quantity);
        } else {
            cartService.save(new Cart(userId, productId, sizeId, quantity));
        }

        return "redirect:/menu";
    }

    @PostMapping("/cart/update/plusByOne")
    public String plusCartByOne(@RequestParam("cartId") String cartId) {
        int cId = Integer.parseInt(cartId);
        cartService.updateCartPlusQuantityByOne(cId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update/minusByOne")
    public String minusCartByOne(@RequestParam("cartId") String cartId, @RequestParam("quantity") int quantity) {
        int cId = Integer.parseInt(cartId);
        if (quantity != 1)
            cartService.updateMinusCartPlusQuantityByOne(cId);
        else
            cartService.deleteById(cId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete")
    public String cartDelete(@RequestParam("cartId") String cartId) {
        int cId = Integer.parseInt(cartId);
        cartService.deleteById(cId);
        return "redirect:/cart";
    }


}
