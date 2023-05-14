package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.CartDetail;
import com.koffe.koffe.repository.ICartRepository;
import com.koffe.koffe.repository.repositoryIMPL.CartRepositoryIMPL;
import com.koffe.koffe.service.ICartService;
import java.util.List;

public class CartServiceIMPL implements ICartService {
    ICartRepository cartRepository = new CartRepositoryIMPL();

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart findById(int id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> findCartByUserId(int userId) {
        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public List<CartDetail> findAllCartDetail() {
        return cartRepository.findAllCartDetail();
    }

    @Override
    public List<CartDetail> findAllCartDetailByUserId(int userId) {
        return cartRepository.findAllCartDetailByUserId(userId);
    }

    @Override
    public boolean save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public boolean update(Cart cart) {
        return cartRepository.update(cart);
    }

    @Override
    public void updatePlusQuantityToCart(int userId, int productId, int sizeId, int quantity) {
        cartRepository.updatePlusQuantityToCart(userId, productId, sizeId, quantity);
    }

    @Override
    public Cart findCartByUserIdAndProductIdAndSizeId(int userId, int productId, int sizeId) {
       return cartRepository.findCartByUserIdAndProductIdAndSizeId(userId, productId, sizeId);
    }

    @Override
    public void updateCartPlusQuantityByOne(int cartId) {
        cartRepository.updateCartPlusQuantityByOne(cartId);
    }

    @Override
    public void updateMinusCartPlusQuantityByOne(int cartId) {
        cartRepository.updateMinusCartPlusQuantityByOne(cartId);
    }

    @Override
    public int getTotalPriceOfUserId(int userId) {
        return cartRepository.getTotalPriceOfUserId(userId);
    }

    @Override
    public void deleteCartByUserId(int userId) {
        cartRepository.deleteCartByUserId(userId);
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
