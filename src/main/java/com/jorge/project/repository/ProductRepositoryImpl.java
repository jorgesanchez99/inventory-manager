package com.jorge.project.repository;

import com.jorge.project.db.DatabaseConnection;
import com.jorge.project.exceptions.ProductException;
import com.jorge.project.model.Category;
import com.jorge.project.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {


    @Override
    public void save(Product product) {
        if (product == null) throw new ProductException("El producto no puede ser nulo");

        String sql = "INSERT INTO products (name, price, stock,category) VALUES (?, ?, ?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, product.getCategory().name());

            int row = statement.executeUpdate();
            if (row == 0) throw new ProductException("No se pudo guardar el producto");

        } catch (SQLException e) {
            throw new ProductException("Error al guardar el producto: " + e.getMessage());

        }

    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        Category.valueOf(resultSet.getString("category"))
                ));
            }

        } catch (SQLException e) {
            throw new ProductException("Error al obtener los productos: " + e.getMessage());
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        Category.valueOf(resultSet.getString("category")
                        ));
            }

        } catch (SQLException e) {
            throw new ProductException("Error al buscar el producto por id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(int id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ?, category = ? WHERE id = ?";
        if (product == null) throw new ProductException("El producto no puede ser nulo");
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setString(4, product.getCategory().name());
            statement.setInt(5, id);

            int row = statement.executeUpdate();
            if (row >= 1) return true;
        } catch (SQLException e) {
            throw new  ProductException("Error al actualizar el producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);
            int row = statement.executeUpdate();
            if (row > 0) return true;
        } catch (SQLException e) {
            throw new ProductException("Error al eliminar el producto: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Product> findByName(String namePart) {
        String sql = "SELECT * FROM products WHERE name ILIKE  ? ";
        if (namePart == null || namePart.isBlank())
            throw new ProductException("El texto de búsqueda no puede estar vacío");
        namePart = "%" + namePart.trim() + "%";
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, namePart);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        Category.valueOf(resultSet.getString("category"))
                ));
            }


        } catch (SQLException e) {
            throw new ProductException("Error al buscar productos por nombre: " + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> findByExactName(String namePart) {
        String sql = "SELECT * FROM products WHERE name ILIKE ? ";
        if (namePart == null || namePart.isBlank())
            throw new ProductException("El texto de búsqueda no puede estar vacío");
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, namePart.trim());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        Category.valueOf(resultSet.getString("category"))
                ));
            }


        } catch (SQLException e) {
            throw new ProductException("Error al buscar productos por nombre: " + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> findByPriceInRange(double min, double max) {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";

        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock"),
                        Category.valueOf(resultSet.getString("category"))
                ));
            }

        } catch (SQLException e) {
            throw new ProductException("Error al buscar productos por rango de precio: " + e.getMessage());
        }
        return products;
    }


}
