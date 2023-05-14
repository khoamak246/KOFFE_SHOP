package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.Cart;
import com.koffe.koffe.model.CartDetail;
import com.koffe.koffe.repository.ICartRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepositoryIMPL implements ICartRepository {

    @Override
    public List<Cart> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Cart> cartList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllCart()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cartList.add(createCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return cartList;
    }

    @Override
    public Cart findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Cart cart = null;
        try {
            callableStatement = conn.prepareCall("{call findCartById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cart = createCart(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return cart;
    }

    @Override
    public List<Cart> findCartByUserId(int userId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Cart> cartList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findCartByUserId(?)}");
            callableStatement.setInt(1, userId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cartList.add(createCart(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return cartList;
    }

    @Override
    public List<CartDetail> findAllCartDetail() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<CartDetail> cartDetail = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllCartDetail()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cartDetail.add(createCartDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return cartDetail;
    }

    @Override
    public List<CartDetail> findAllCartDetailByUserId(int userId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<CartDetail> cartDetail = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllCartDetailByUserId(?)}");
            callableStatement.setInt(1, userId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cartDetail.add(createCartDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return cartDetail;
    }

    @Override
    public boolean save(Cart cart) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveCart(?, ?, ?, ?)}");
            callableStatement.setInt(1, cart.getUserId());
            callableStatement.setInt(2, cart.getProductId());
            callableStatement.setInt(3, cart.getSizeId());
            callableStatement.setInt(4, cart.getQuantity());
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean update(Cart cart) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateCart(?, ?)}");
            callableStatement.setInt(1, cart.getCartId());
            callableStatement.setInt(2, cart.getQuantity());
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public void updatePlusQuantityToCart(int userId, int productId, int sizeId, int quantity) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updatePlusQuantityToCart(?, ?, ?, ?)}");
            callableStatement.setInt(1, userId);
            callableStatement.setInt(2, productId);
            callableStatement.setInt(3, sizeId);
            callableStatement.setInt(4, quantity);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public Cart findCartByUserIdAndProductIdAndSizeId(int userId, int productId, int sizeId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Cart cart = null;
        try {
            callableStatement = conn.prepareCall("{call findCartByUserIdAndProductIdAndSizeId(?, ?, ?)}");
            callableStatement.setInt(1, userId);
            callableStatement.setInt(2, productId);
            callableStatement.setInt(3, sizeId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                cart = createCart(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return cart;
    }

    @Override
    public void updateCartPlusQuantityByOne(int cartId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateCartPlusQuantityByOne(?)}");
            callableStatement.setInt(1, cartId);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public void updateMinusCartPlusQuantityByOne(int cartId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateMinusCartPlusQuantityByOne(?)}");
            callableStatement.setInt(1, cartId);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public int getTotalPriceOfUserId(int userId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getTotalPriceOfUserId(?)}");
            callableStatement.setInt(1, userId);
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
    public void deleteCartByUserId(int userId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call deleteCartByUserId(?)}");
            callableStatement.setInt(1, userId);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public void deleteById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call deleteCartById(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    private Cart createCart(ResultSet rs) {
        Cart cart = null;
        try {
                int cartId = rs.getInt(1);
                int userId = rs.getInt(2);
                int productId = rs.getInt(3);
                int sizeId = rs.getInt(4);
                int price = rs.getInt(5);
                int total = rs.getInt(6);
                int quantity = rs.getInt(7);
                Timestamp date = rs.getTimestamp(8);
                cart = new Cart(cartId, userId, productId, sizeId, price, quantity, total, date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    private CartDetail createCartDetail(ResultSet rs) {
        CartDetail cartDetail;
        try {
            int cartId = rs.getInt(1);
            int userId = rs.getInt(2);
            int productId = rs.getInt(3);
            int sizeId = rs.getInt(4);
            String productName = rs.getString(5);
            String sizeDetail = rs.getString(6);
            int price = rs.getInt(7);
            int quantity = rs.getInt(8);
            int total = rs.getInt(9);
            String avatar = rs.getString(10);
            Timestamp date = rs.getTimestamp(11);
            cartDetail = new CartDetail(
                    cartId,
                    userId,
                    productId,
                    sizeId,
                    productName,
                    sizeDetail,
                    price,
                    quantity,
                    total,
                    avatar,
                    date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartDetail;
    }
}
