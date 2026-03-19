package com.jorge.project.repository;

import com.jorge.project.model.Product;

import java.util.List;

public interface IProductRepository {

    void save(Product product);
    List<Product> findAll();
    Product findById(int id);
    boolean update(int id, Product product);
    boolean delete(int id);
    List<Product> findByName(String namePart);
    List<Product> findByPriceInRange(double min, double max);
}
