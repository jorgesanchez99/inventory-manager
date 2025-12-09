package com.jorge.project.service;

import com.jorge.project.model.Category;
import com.jorge.project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private List<Product> products;
    private int nextId = 1;

    public ProductServiceImpl() {
        this.products = new ArrayList<>();
        create(new Product("Laptop Lenovo ThinkPad", 3200.00, 5, Category.ELECTRONIC));
        create(new Product("Silla Ergonómica de Oficina", 450.00, 12, Category.OFFICE));
        create(new Product("Polera Deportiva Nike", 159.90, 20, Category.FASHION));
        create(new Product("LEGO Star Wars X-Wing", 899.00, 8, Category.TOYS));
        create( new Product("Pelota de Fútbol Profesional", 120.00, 15, Category.SPORTS));

    }

    @Override
    public void create(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Product findById(int id) {
        return products.stream().filter(product1 -> product1.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean update(int id, Product product) {
        Product productExisting = findById(id);
        if (productExisting == null) return false;
        productExisting.setName(product.getName());
        productExisting.setPrice(product.getPrice());
        productExisting.setCategory(product.getCategory());
        productExisting.setStock(product.getStock());
        return true;
    }

    @Override
    public boolean delete(int id) {
        Product product = findById(id);
        if (product == null) return false;
        return products.remove(product);
    }

    @Override
    public List<Product> findByName(String namePart) {
        if (namePart == null || namePart.isBlank()) return List.of(); // list empty
        return products.stream().filter(product ->
                product.getName().toLowerCase().contains(namePart.toLowerCase())).toList();
    }

    @Override
    public List<Product> findByPriceInRange(double min, double max) {
        return products.stream().filter(product -> product.getPrice() >= min && product.getPrice() <= max).toList();
    }
}
