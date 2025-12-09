package com.jorge.project.controller;

import com.jorge.project.model.Product;
import com.jorge.project.service.IProductService;

import java.util.List;

public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    public void createProduct(Product product) {
        productService.create(product);
    }

    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    public Product getProductById(int id) {
        return productService.findById(id);
    }

    public boolean updateProduct(int id, Product product) {
        return productService.update(id, product);
    }


    public boolean deleteProduct(int id) {
        return productService.delete(id);
    }

    public List<Product> getProductsByName(String namePart) {
        return productService.findByName(namePart);
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productService.findByPriceInRange(minPrice, maxPrice);
    }

}
