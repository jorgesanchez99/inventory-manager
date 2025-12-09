package com.jorge.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jorge.project.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private List<Product> products;
    private int nextId = 1;
    private final String PATH = "src/products.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ProductServiceImpl() {
        loadJson();
    }

    @Override
    public void create(Product product) {
        product.setId(nextId++);
        products.add(product);
        saveToJson();
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

        saveToJson();

        return true;
    }

    @Override
    public boolean delete(int id) {
        Product product = findById(id);
        if (product == null) return false;
        if (products.remove(product)) {
            saveToJson();
            return true;
        } else {
            return false;
        }
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


    private void saveToJson() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            gson.toJson(products, bw);
        } catch (IOException e) {
            System.out.println("Error saving products : " + e.getMessage());
        }
    }


    private void loadJson() {
        File file = new File(PATH);
        if (!file.exists()) {
            this.nextId = 1;
            this.products = new ArrayList<>();
            System.err.println("File not found, starting with an empty product list.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            Product[] productsArray = gson.fromJson(br, Product[].class);
            if (productsArray != null) {
                this.products = new ArrayList<>(Arrays.asList(productsArray));
                this.nextId = products.stream().mapToInt(Product::getId).max().orElse(0) + 1;
            } else {
                this.products = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
