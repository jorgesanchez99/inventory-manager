package com.jorge.project;

import com.jorge.project.controller.ProductController;
import com.jorge.project.db.DatabaseConnection;
import com.jorge.project.repository.IProductRepository;
import com.jorge.project.repository.ProductRepositoryImpl;
import com.jorge.project.service.IProductService;
import com.jorge.project.service.ProductServiceImpl;
import com.jorge.project.ui.UI;


public class App {
    public static void main(String[] args) {
        IProductRepository productRepository = new ProductRepositoryImpl();
        IProductService productService = new ProductServiceImpl(productRepository);
        ProductController productController = new ProductController(productService);
        UI ui = new UI(productController);
        ui.run();
        DatabaseConnection.closePool();
    }
}
