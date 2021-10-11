package com.example.demo.application;


import com.example.demo.domain.Product;

import java.util.List;


public interface ApplicationService {
    List<Product> getProductsByName(String name);
    List<Product> getProductsWithSameName();
    List<Product> getAll();
    void save(Product product);
    void delete(Product product);
    Product getProductById(Long id);
}
