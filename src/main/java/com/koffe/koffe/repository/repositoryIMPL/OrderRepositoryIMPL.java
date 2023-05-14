package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.Order;
import com.koffe.koffe.model.Order_Amount_Product;
import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.IOrderRepository;
import com.koffe.koffe.service.serviceIMPL.UserServiceIMPL;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryIMPL implements IOrderRepository {
    @Override
    public List<Order> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllOrder()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                orders.add(createOrder(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return orders;
    }

    @Override
    public Order findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Order order = null;
        try {
            callableStatement = conn.prepareCall("{call findOrderById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                order = createOrder(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return order;
    }

    @Override
    public boolean save(Order order) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveOrder(?, ?, ?)}");
            callableStatement.setInt(1, order.getUserId());
            callableStatement.setInt(2, order.getAddressId());
            callableStatement.setInt(3, order.getTotal());
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
    public List<Order> findAllOrderByUserId(int userId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Order> orders = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllOrderByUserId(?)}");
            callableStatement.setInt(1, userId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                orders.add(createOrder(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return orders;
    }

    @Override
    public void updateStatusOrderByOrderId(int orderId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateStatusOrderByOrderId(?)}");
            callableStatement.setInt(1, orderId);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public int getLastOrderId() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getLastOrderId()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return 0;
    }

    @Override
    public List<Integer> getAllOrderSize(List<Order> orders) {
        List<Integer> orderSizeList = new ArrayList<>();
        for (Order order: orders) {
            int orderSize = new OrderItemsRepositoryIMPL().findAllOrderItemsByOrderId(order.getOrderId()).size();
            orderSizeList.add(orderSize);
        }
        return orderSizeList;
    }

    @Override
    public int getCountOfOrder() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getCountOfOrder()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return 0;
    }

    @Override
    public int getSumTotalOrder() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getSumTotalOrder()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return 0;
    }

    @Override
    public List<Order_Amount_Product> getOrderAmountProduct() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Order_Amount_Product> amountOrderProduct = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call getOrderAmountProduct()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                String name = rs.getString(2);
                int totalQuantity = rs.getInt(3);
               amountOrderProduct.add(new Order_Amount_Product(productId, name, totalQuantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return amountOrderProduct;
    }

    @Override
    public List<Integer> getTotalEachMonthInYear() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Integer> totalEachMonth = new ArrayList<>();
        for (int i = 1; i <= 12; i++ ) {
            totalEachMonth.add(0);
        }
        try {
            callableStatement = conn.prepareCall("{call getTotalEachMonthInYear()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int month = rs.getInt(1);
                totalEachMonth.set(month - 1, rs.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return totalEachMonth;
    }

    @Override
    public Order findAcceptedOrderById(int orderId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Order order = null;
        try {
            callableStatement = conn.prepareCall("{call findAcceptedOrderById(?)}");
            callableStatement.setInt(1, orderId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                order = createOrder(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return order;
    }


    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    private Order createOrder(ResultSet rs) {
        Order order;
        try {
            int orderId = rs.getInt(1);
            int user_id = rs.getInt(2);
            int addressId = rs.getInt(3);
            int total = rs.getInt(4);
            boolean status = rs.getBoolean(5);
            Timestamp date = rs.getTimestamp(6);
            order = new Order(orderId, user_id, addressId, total, status, date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
