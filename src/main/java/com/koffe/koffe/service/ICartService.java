package com.koffe.koffe.service;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.CartDetail;
import com.koffe.koffe.service.design.IService;

import java.util.List;

public interface ICartService extends IService<Cart> {
    List<Cart> findCartByUserId(int userId);
    List<CartDetail> findAllCartDetail();
    List<CartDetail> findAllCartDetailByUserId(int userId);
    void updatePlusQuantityToCart(int userId, int productId, int sizeId, int quantity);
    Cart findCartByUserIdAndProductIdAndSizeId(int userId, int productId, int sizeId);
    void updateCartPlusQuantityByOne(int cartId);
    void updateMinusCartPlusQuantityByOne(int cartId);
    int getTotalPriceOfUserId(int userId);
    void deleteCartByUserId(int userId);
}
