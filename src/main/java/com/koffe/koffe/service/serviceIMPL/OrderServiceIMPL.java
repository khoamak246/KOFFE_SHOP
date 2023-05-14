package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.Order;
import com.koffe.koffe.model.Order_Amount_Product;
import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.IOrderRepository;
import com.koffe.koffe.repository.repositoryIMPL.OrderRepositoryIMPL;
import com.koffe.koffe.service.IOrderService;
import com.koffe.koffe.utils.Validate;

import java.util.List;

public class OrderServiceIMPL implements IOrderService {
    IOrderRepository orderRepository = new OrderRepositoryIMPL();

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public boolean save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public int getLastOrderId() {
         return orderRepository.getLastOrderId();
    }

    @Override
    public List<Order> findAllOrderByUserId(int userId) {
        return orderRepository.findAllOrderByUserId(userId);
    }

    @Override
    public void updateStatusOrderByOrderId(int orderId) {
        orderRepository.updateStatusOrderByOrderId(orderId);
    }

    @Override
    public List<Integer> getAllOrderSize(List<Order> orders) {
        return orderRepository.getAllOrderSize(orders);
    }

    @Override
    public int getCountOfOrder() {
        return orderRepository.getCountOfOrder();
    }

    @Override
    public int getSumTotalOrder() {
        return orderRepository.getSumTotalOrder();
    }

    @Override
    public List<Order_Amount_Product> getOrderAmountProduct() {
        return orderRepository.getOrderAmountProduct();
    }

    @Override
    public List<Integer> getTotalEachMonthInYear() {
        return orderRepository.getTotalEachMonthInYear();
    }

    @Override
    public boolean isNumber(String number) {
        return Validate.isNumber(number);
    }

    @Override
    public Order findAcceptedOrderById(int orderId) {
        return orderRepository.findAcceptedOrderById(orderId);
    }


}
