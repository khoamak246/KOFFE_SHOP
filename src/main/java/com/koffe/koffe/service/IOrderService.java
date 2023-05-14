package com.koffe.koffe.service;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.Order;
import com.koffe.koffe.model.Order_Amount_Product;
import com.koffe.koffe.model.User;
import com.koffe.koffe.service.design.IService;

import java.util.List;

public interface IOrderService extends IService<Order> {
    int getLastOrderId();
    List<Order> findAllOrderByUserId(int userId);
    void updateStatusOrderByOrderId(int orderId);
    List<Integer> getAllOrderSize(List<Order> orders);
    int getCountOfOrder();
    int getSumTotalOrder();
    List<Order_Amount_Product> getOrderAmountProduct();
    List<Integer> getTotalEachMonthInYear();
    boolean isNumber(String number);
    Order findAcceptedOrderById(int orderId);
}
