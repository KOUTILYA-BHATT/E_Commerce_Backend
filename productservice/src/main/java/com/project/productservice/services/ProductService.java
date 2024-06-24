package com.project.productservice.services;

import com.project.productservice.models.Product;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Product createProduct(Product product);
    Product deleteProduct();
}
