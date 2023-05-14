package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.OrderItems;
import com.koffe.koffe.model.OrderItemsDetail;
import com.koffe.koffe.repository.IOrderItemsRepository;
import com.koffe.koffe.repository.repositoryIMPL.OrderItemsRepositoryIMPL;
import com.koffe.koffe.service.IOrderItemsService;

import java.util.List;

public class OrderItemsServiceIMPL implements IOrderItemsService {

    IOrderItemsRepository orderItemsRepository = new OrderItemsRepositoryIMPL();

    @Override
    public List<OrderItems> findAll() {
        return null;
    }

    @Override
    public OrderItems findById(int id) {
        return null;
    }

    @Override
    public boolean save(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }

    @Override
    public boolean update(OrderItems orderItems) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void saveListCartToOrderItem(int orderId, List<Cart> cartList) {
        for (Cart cart : cartList) {
            int productId = cart.getProductId();
            int sizeId = cart.getSizeId();
            int price = cart.getPrice();
            int quantity = cart.getQuantity();
            save(new OrderItems(orderId, productId, sizeId, price, quantity));
        }
    }

    @Override
    public List<OrderItems> findAllOrderItemsByOrderId(int orderId) {
        return orderItemsRepository.findAllOrderItemsByOrderId(orderId);
    }

    @Override
    public List<OrderItemsDetail> findOrderItemsDetailByOrderId(int orderId) {
        return orderItemsRepository.findOrderItemsDetailByOrderId(orderId);
    }
}
