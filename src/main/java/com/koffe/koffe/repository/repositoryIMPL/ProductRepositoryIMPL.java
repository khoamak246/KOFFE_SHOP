package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.Product;
import com.koffe.koffe.model.ProductDetail;
import com.koffe.koffe.repository.IProductRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryIMPL implements IProductRepository {
    @Override
    public List<Product> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> productList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllProducts()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                productList.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return productList;
    }

    @Override
    public List<Product> findAllProductsBySize(int sizeId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> productList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllProductsBySize(?)}");
            callableStatement.setInt(1, sizeId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                productList.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return productList;
    }

    @Override
    public Product findProductByProductIdAndSizeId(int productId, int sizeId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Product product = null;
        try {
            callableStatement = conn.prepareCall("{call findProductByProductIdAndSizeId(?, ?)}");
            callableStatement.setInt(1, productId);
            callableStatement.setInt(2, sizeId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                product = createProduct(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return product;
    }



    @Override
    public Product findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Product product = null;
        try {
            callableStatement = conn.prepareCall("{call findProductById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                product = createProduct(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return product;
    }

    @Override
    public List<Product> findByIdProduct(int productId) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> products = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findListProductById(?)}");
            callableStatement.setInt(1, productId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                products.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return products;
    }



    @Override
    public boolean save(ProductDetail productDetail) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveProducts(?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, productDetail.getProductName());
            callableStatement.setString(2, productDetail.getProductDescription());
            callableStatement.setString(3, productDetail.getAvatar());
            callableStatement.setInt(4, productDetail.getPriceS());
            callableStatement.setInt(5, productDetail.getPriceM());
            callableStatement.setInt(6, productDetail.getPriceL());
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
    public boolean save(Product product) {
        return false;
    }

    @Override
    public void updateProduct(ProductDetail productDetail) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateProducts(?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, productDetail.getProductId());
            callableStatement.setString(2, productDetail.getProductName());
            callableStatement.setString(3, productDetail.getProductDescription());
            callableStatement.setString(4, productDetail.getAvatar());
            callableStatement.setInt(5, productDetail.getPriceS());
            callableStatement.setInt(6, productDetail.getPriceL());
            callableStatement.setInt(7, productDetail.getPriceM());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public List<Product> findProductByName(String name) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> productList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findProductByName(?)}");
            callableStatement.setString(1, name);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                productList.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return productList;
    }

    @Override
    public List<Product> findAllActiveProduct() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> productList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllActiveProduct()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                productList.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return productList;
    }

    @Override
    public List<Product> findAllNotActiveProduct() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Product> productList = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllNotActiveProduct()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                productList.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return productList;
    }

    @Override
    public void changeStatusProductById(int productId, boolean status) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call changeStatusProductById(?, ?)}");
            callableStatement.setInt(1, productId);
            if (status)
                callableStatement.setInt(2, 1);
            else
                callableStatement.setInt(2, 0);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public void deleteById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call deleteProductsById(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    private Product createProduct(ResultSet rs) {
        Product product;
        try {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                String productDescription = rs.getString(3);
                int sizeId = rs.getInt(4);
                int price = rs.getInt(5);
                String productAvatar = rs.getString(6);
                boolean status = rs.getBoolean(7);
                product = new Product(productId, productName, productDescription, sizeId, price, productAvatar, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
