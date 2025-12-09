package com.jorge.project;

import com.jorge.project.controller.ProductController;
import com.jorge.project.service.IProductService;
import com.jorge.project.service.ProductServiceImpl;
import com.jorge.project.ui.UI;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        IProductService productService = new ProductServiceImpl();
        ProductController productController = new ProductController(productService);
        UI ui = new UI(productController);
        ui.run();
    }
}
