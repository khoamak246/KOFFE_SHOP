package com.koffe.koffe.service;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.OrderItems;
import com.koffe.koffe.model.OrderItemsDetail;
import com.koffe.koffe.service.design.IService;

import java.util.List;

public interface IOrderItemsService extends IService<OrderItems> {
    void saveListCartToOrderItem(int orderId, List<Cart> cartList);
    List<OrderItems> findAllOrderItemsByOrderId(int orderId);
    List<OrderItemsDetail> findOrderItemsDetailByOrderId(int orderId);
}
