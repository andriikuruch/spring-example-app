package com.example.demo.application;


import com.example.demo.domain.Product;
import com.example.demo.domain.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "singleton")
public class ApplicationServiceImpl implements ApplicationService {
    private final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProductsByName(String name) throws IllegalArgumentException {
        logger.trace("Enter in applicationService.getProductsByName(String name) with arg name=" + name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name should be not empty string");
        }

        List<Product> result = repository.getProductsByName(name);
        logger.debug("List to return", result);
        logger.trace("Exit from applicationService.getProductsByName(String name)");
        return result;
    }

    public List<Product> getProductsWithSameName() {
        logger.trace("Enter in applicationService.getProductsWithSameName() without args");
        List<Product> result = new ArrayList<>();
        for (Product product: repository.getAll()) {
            if (!result.contains(product)) {
                List<Product> tmp = repository.getProductsByName(product.getName());
                if (tmp.size() > 1) { // exclude case when result is null or get self product
                    result.addAll(tmp);
                }
            }
        }
        logger.debug("List to return", result);
        logger.trace("Exit from applicationService.getProductsWithSameName()");
        return result;
    }

    public void save(Product product) {
        logger.trace("Enter in applicationService.save(Product product) with arg product=" + product.toString());
        repository.save(product);
        logger.debug("Nothing to return");
        logger.trace("Exit from applicationService.save(Product product)");
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        return repository.getByID(id);
    }

    public List<Product> getAll() {
        logger.trace("Enter in applicationService.getAll() without args");
        List<Product> result = repository.getAll();
        logger.debug("List to return", result);
        logger.trace("Exit from applicationService.getAll()");
        return result;
    }
}
