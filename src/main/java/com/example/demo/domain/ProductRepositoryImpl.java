package com.example.demo.domain;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    private final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    private SessionFactory sessionFactory;

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product getByID(Long id) {
        logger.trace("Enter in productRepository.getByID(Long id) with arg id=" + id);
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        logger.trace("Exit from productRepository.getByID(Long id)");
        return product;
    }

    public void save(Product product) {
        logger.trace("Enter in productRepository.save(Product product) with arg product=" + product.toString());
        sessionFactory.getCurrentSession().save(product);
        logger.trace("Exit from productRepository.save(Product product)");
    }

    public void delete(Product product) {
        logger.trace("Enter in productRepository.delete(Product product) with arg product=" + product.toString());
        sessionFactory.getCurrentSession().delete(product);
        logger.trace("Exit from productRepository.delete(Product product)");
    }

    public void update(Product product) {
        logger.trace("Enter in productRepository.update(Product product) with arg product=" + product.toString());
        sessionFactory.getCurrentSession().update(product);
        logger.trace("Exit from productRepository.update(Product product)");
    }

    public List<Product> getAll() {
        logger.trace("Enter in productRepository.getAll() without args");
        Query<Product> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT P FROM Product P", Product.class);
        List<Product> result = query.getResultList();
        logger.trace("Exit from productRepository.getAll()");
        return result;
    }

    public List<Product> getProductsByName(String name) {
        logger.trace("Enter in productRepository.getProductsByName(String name) with arg name=" + name);
        Query<Product> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT P FROM Product P WHERE P.name = :name", Product.class)
                .setParameter("name", name);
        List<Product> result = query.getResultList();
        logger.trace("Exit from productRepository.getProductsByName(String name)");
        return result;
    }
}
