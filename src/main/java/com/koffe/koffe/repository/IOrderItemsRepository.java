package com.koffe.koffe.repository;

import com.koffe.koffe.model.OrderItems;
import com.koffe.koffe.model.OrderItemsDetail;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface IOrderItemsRepository extends IRepository<OrderItems> {
    List<OrderItems> findAllOrderItemsByOrderId(int orderId);
    List<OrderItemsDetail> findOrderItemsDetailByOrderId(int orderId);
}
