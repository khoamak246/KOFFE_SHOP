package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Product;
import com.koffe.koffe.model.ProductDetail;
import com.koffe.koffe.repository.IProductRepository;
import com.koffe.koffe.repository.repositoryIMPL.ProductRepositoryIMPL;
import com.koffe.koffe.service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService {
    IProductRepository productRepository = new ProductRepositoryIMPL();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsBySize(int sizeId) {
        return productRepository.findAllProductsBySize(sizeId);
    }


    @Override
    public Product findProductByProductIdAndSizeId(int productId, int sizeId) {
        return productRepository.findProductByProductIdAndSizeId(productId, sizeId);
    }

    @Override
    public boolean save(ProductDetail productDetail) {
        return productRepository.save(productDetail);
    }

    @Override
    public List<Product> findByIdProduct(int productId) {
        return productRepository.findByIdProduct(productId);
    }

    @Override
    public void updateProduct(ProductDetail productDetail) {
        productRepository.updateProduct(productDetail);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> findAllActiveProduct() {
        return productRepository.findAllActiveProduct();
    }

    @Override
    public List<Product> findAllNotActiveProduct() {
        return productRepository.findAllNotActiveProduct();
    }

    @Override
    public void changeStatusProductById(int productId, boolean status) {
        productRepository.changeStatusProductById(productId, status);
    }

    @Override
    public boolean isOnSale(int productId) {
        Product product = productRepository.findById(productId);
        return product.isStatus();
    }

    @Override
    public List<ProductDetail> getListActiveProductFromListProduct(List<Product> productList) {
        List<ProductDetail> productDetailList = new ArrayList<>();
        for (Product product: productList) {
            boolean isOnSaleProduct = isOnSale(product.getProductId());
            if (isOnSaleProduct) {
                List<Product> products = findByIdProduct(product.getProductId());
                if (!products.isEmpty()){
                    int productId = products.get(0).getProductId();
                    String name = products.get(0).getProductName();
                    String description = products.get(0).getProductDescription();
                    String avatar = products.get(0).getProductAvatar();
                    int priceS = products.get(0).getPrice();
                    int priceM = products.get(1).getPrice();
                    int priceL = products.get(2).getPrice();
                    ProductDetail tempProductDetail = new ProductDetail(productId, name, description, avatar, priceS, priceM, priceL);
                    productDetailList.add(tempProductDetail);
                }
            }
        }
        return productDetailList;
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }


}
