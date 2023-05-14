package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.OrderItems;
import com.koffe.koffe.model.OrderItemsDetail;
import com.koffe.koffe.repository.IOrderItemsRepository;
import com.koffe.koffe.repository.design.IRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsRepositoryIMPL implements IOrderItemsRepository {
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
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveOrderItems(?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, orderItems.getOrderId());
            callableStatement.setInt(2, orderItems.getProductId());
            callableStatement.setInt(3, orderItems.getSizeId());
            callableStatement.setInt(4, orderItems.getPrice());
            callableStatement.setInt(5, orderItems.getQuantity());
            int response = callableStatement.executeUpdate();
            if (response == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean update(OrderItems orderItems) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<OrderItems> findAllOrderItemsByOrderId(int orderId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<OrderItems> orderItems = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllOrderItemsByOrderId(?)}");
            callableStatement.setInt(1, orderId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                orderItems.add(createOrderItems(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return orderItems;
    }

    @Override
    public List<OrderItemsDetail> findOrderItemsDetailByOrderId(int orderId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<OrderItemsDetail> orderItemsDetails = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findOrderItemsDetailByOrderId(?)}");
            callableStatement.setInt(1, orderId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                orderItemsDetails.add(createOrderItemsDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return orderItemsDetails;
    }

    private OrderItems createOrderItems(ResultSet rs) {
        OrderItems orderItems;
        try {
            int orderItemsId = rs.getInt(1);
            int order_id = rs.getInt(2);
            int productId = rs.getInt(3);
            int sizeId = rs.getInt(4);
            int price = rs.getInt(5);
            int quantity = rs.getInt(6);
            orderItems = new OrderItems(orderItemsId, order_id, productId, sizeId, price, quantity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    private OrderItemsDetail createOrderItemsDetail(ResultSet rs) {
        OrderItemsDetail orderItemsDetail;
        try {
            int orderItemsId = rs.getInt(1);
            String productName = rs.getString(2);
            String sizeDetail = rs.getString(3);
            int quantity = rs.getInt(4);
            int total = rs.getInt(5);
            String productAvatar = rs.getString(6);
            orderItemsDetail = new OrderItemsDetail(orderItemsId, productName, sizeDetail, quantity, total, productAvatar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItemsDetail;
    }
}
