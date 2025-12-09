package com.jorge.project.model;

import com.jorge.project.exceptions.ProductException;

import java.util.Objects;

public class Product {


    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;

    public Product(String name, double price, int stock, Category category) {
        this.name = nonBlank(name, "Nombre");
        this.price = positivePrice(price, "Precio");
        this.stock = nonNegativeStock(stock, "Stock");
        this.category = validateCategory(category, "Categoría");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new ProductException("ID debe ser un número positivo");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = nonBlank(name, "Nombre");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = positivePrice(price, "Precio");
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = nonNegativeStock(stock, "Stock");
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = validateCategory(category, "Categoría");
    }

    private String nonBlank(String raw, String fieldName) {
        if (raw == null || raw.trim().isBlank()) {
            throw new ProductException(fieldName + " no puede estar vacío");
        }
        return raw.trim();
    }

    private int nonNegativeStock(int raw, String fieldName) {
        if (raw < 0) {
            throw new ProductException(fieldName + " no puede ser negativo");
        }
        return raw;
    }

    private double positivePrice(double raw, String fieldName) {
        if (raw <= 0) {
            throw new ProductException(fieldName + " debe ser mayor que cero");
        }
        return raw;
    }

    private Category validateCategory(Category category, String fieldName) {
        if (category == null) {
            throw new ProductException(fieldName + " no puede ser nulo");
        }
        return category;
    }

    @Override
    public String toString() {
        return "Producto #%d: %s | Precio: $%.2f | Stock: %d unidades | Categoría: %s"
                .formatted(id, name, price, stock, category);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
