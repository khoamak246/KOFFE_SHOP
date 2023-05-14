package com.koffe.koffe.repository;

import com.koffe.koffe.model.Product;
import com.koffe.koffe.model.ProductDetail;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface IProductRepository extends IRepository<Product> {
    List<Product> findAllProductsBySize(int sizeId);
    Product findProductByProductIdAndSizeId (int productId, int sizeId);
    boolean save(ProductDetail productDetail);
    List<Product> findByIdProduct(int productId);
    void updateProduct(ProductDetail productDetail);
    List<Product> findProductByName(String name);
    List<Product> findAllActiveProduct();
    List<Product> findAllNotActiveProduct();
    void changeStatusProductById(int productId, boolean status);
}
