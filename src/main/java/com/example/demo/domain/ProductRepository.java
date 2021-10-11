package com.example.demo.domain;

import java.util.List;


public interface ProductRepository {
    Product getByID(Long id);
    void save(Product product);
    void delete(Product product);
    void update(Product product);
    List<Product> getAll();
    List<Product> getProductsByName(String name);
}
