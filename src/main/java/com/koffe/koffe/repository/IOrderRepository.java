package com.koffe.koffe.repository;

import com.koffe.koffe.model.Order;
import com.koffe.koffe.model.Order_Amount_Product;
import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface IOrderRepository extends IRepository<Order> {
    List<Order> findAllOrderByUserId(int userId);
    void updateStatusOrderByOrderId(int orderId);
    int getLastOrderId();
    List<Integer> getAllOrderSize(List<Order> orders);
    int getCountOfOrder();
    int getSumTotalOrder();
    List<Order_Amount_Product> getOrderAmountProduct();
    List<Integer> getTotalEachMonthInYear();
    Order findAcceptedOrderById(int orderId);
}
